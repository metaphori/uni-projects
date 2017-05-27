<?php
session_start();
?>

<html>

<head>
	<style type="text/css">
	p.warn { color:#f00; font-size:14pt; }
	
	p.prenot { 
		border-left:10px solid #ababab;
		border-bottom:1px solid #000;
		padding:8px;
		background-color:#ededed;
	}
	
	tr.rigo td{
		border-bottom:1px solid #007;
		padding-top: 4px;
		text-align: center;
	}
	</style>
</head>

<body>

<a href="admin.php">Amministrazione</a>. <a href="index.php">Front end</a>.<hr /><br />

<?php

// CONTROLLO ACCESSO AMMINISTRAZIONE
if(isset($_POST['pass'])){
	if($_POST['pass']=="topolino"){
		echo "<p class=\"warn\">Accesso effettuato</p>";
		$_SESSION['admin']="ok";
	}
	else echo "<p class=\"warn\">Impossibile effettuare l'accesso. Password errata.</p>";
}

// FORM PER ACCESSO AMMINISTRAZIONE
if(!isset($_SESSION['admin'])){
?>
<form action="admin.php" method="post">
Inserire password per accesso all'area di amministrazione: <input type="password" name="pass" /><br />
<input type="submit" name="Entra" />
</form>
<?php
}
// AMMINISTRAZIONE - ACCESSO AUTORIZZATO
else{

?>
<ul>
<li><a href="?todo=logout">Logout</a></li>
<li><a href="?todo=calendario">Calendario</a></li>
<li><a href="?todo=prenotazioni">Prenotazioni in attesa di conferma</a></li>
<li><a href="?todo=addtorneo">Inserisci un nuovo torneo</a></li>
<li><a href="?todo=programmazione">Programmazione tornei</a></li>
</ul>

<?php
// OPERAZIONE DI AMMINISTRAZIONE DA SVOLGERE
if(isset($_GET['todo'])){

	$cn = mysql_connect("localhost","root","enter");
	mysql_select_db("sportcore", $cn);

	// LOGOUT
	if($_GET['todo']=="logout"){
		unset($_SESSION['admin']);
		session_destroy();
		echo "<p class=\"warn\">Logout effettuato correttamente</p>";
	}
	//GESTIONE PRENOTAZIONI
	else if($_GET['todo']=="prenotazioni"){
		echo "<h3>Prenotazioni da confermare</h3>";
		$selp = mysql_query("SELECT P.Id, DataOraInizio, DataOraFine, C.Nome N1, U.Nome N2, Cognome, Telefono, Email, Username
		FROM Prenotazioni_utente P, Campi C, Utenti U
		WHERE Confermata='0' AND P.Campo=C.Id AND P.Utente=U.Id
		ORDER BY DataOraInizio ASC") or die(mysql_error());
		if($selp && mysql_num_rows($selp)>0){
			while ($r=mysql_fetch_array($selp)){
				$email = strlen($r['Email'])==0 ? "email assente" : $r['Email'];
				echo "<p class=\"prenot\">Prenotazione sul campo <b>" . $r['N1'] . "</b> da parte dell'utente " .
				$r['Username'] . " (" . $r['N2'] . " " . $r['Cognome'] . ", " . $r['Telefono'] .",
				 " . $email . ").<br />";
				echo "Giorno e ora: " . $r['DataOraInizio'] . " -- " . $r['DataOraFine'] . ".<br />";
				echo "<a href=\"?todo=conferma&amp;idp=".$r['Id']."\">Conferma questa prenotazione</a> oppure 
				<a href=\"?todo=disdici&amp;idp=".$r['Id']."\">disdici questa prenotazione</a>.</p>";
			}
		}
		else{
			echo "<p>Non ci sono al momento prenotazioni in attesa di conferma.</p>";
		}
		
	}
	else if($_GET['todo']=="conferma"){
		$idp = $_GET['idp'];
		
		$upd = mysql_query("UPDATE Prenotazioni_Utente SET Confermata='1' WHERE Id='$idp'");
		if($upd){
			echo "<p>Operazione effettuata correttamente. Ora occorrerà informare l'utente dell'avvenuta 
			conferma della prenotazione.</p>";
		}
		else{
			echo "<p class=\"warn\">Si è verificato un errore durante l'operazione.</p>";
		}
	}
	else if ($_GET['todo']=="disdici"){
		$idp = $_GET['idp'];
		
		$upd = mysql_query("DELETE FROM Prenotazioni_Utente WHERE Id='$idp'");
		if($upd){
			echo "<p>Operazione effettuata correttamente. La prenotazione è stata cancellata.
			Ora occorrerà informare l'utente dell'avvenuta cancellazione. </p>";
		}
		else{
			echo "<p class=\"warn\">Si è verificato un errore durante l'operazione.</p>";
		}	
	}
	// GESTIONE TORNEI
	else if($_GET['todo']=="addtorneo"){
?>
<h3>Istituisci un nuovo torneo</h3>

<form action="?todo=addtorneo" method="post">
Titolo torneo <input type="text" name="nome" /><br /><br />
Apertura iscrizioni <input type="text" name="apert" value="yyyy/mm/gg hh" /><br /><br />
Chiusura iscrizioni <input type="text" name="chius" value="yyyy/mm/gg hh" /><br /><br />
Premio <input type="text" name="premio" /><br /><br />
Tassa iscrizione <input type="text" name="tassa" /><br /><br />
Minimo numero iscritti <input type="text" name="minn" /><br /><br />
Massimo numero iscritti <input type="text" name="maxn" /><br /<br />
<input type="submit" name="send" value="Crea!" />
</form>


<?php
	if(isset($_POST['send'])){
		$ins = "INSERT INTO Tornei(Id, Nome, AperturaIscrizioni, ChiusuraIscrizioni,
		Premio, TassaIscrizione, MinNumIscritti, MaxNumIscritti)
		VALUES(NULL, '".mysql_escape_string($_POST['nome'])."', '".$_POST['apert']."', '".$_POST['chius']."', '".$_POST['premio']."',
		 '".$_POST['tassa']."', '".$_POST['minn']."', '".$_POST['maxn']."')";
		
		//echo "<pre>$ins</pre>";
		
		$res = mysql_query($ins) or die(mysql_error());
		if($res){
			echo "<p>Torneo creato correttamente.</p>";
		}
		else{
			echo "<p class=\"warn\">Si è verificato un errore durante la creazione del torneo.</p>";
		}
	
	}


	} // todo = addtorneo
	else if($_GET['todo']=="programmazione"){
		if(!isset($_GET['idt'])){
?>
<form action="<?php echo $_SERVER['PHP_SELF']; ?>" method="get">
<input type="hidden" name="todo" value="programmazione" />
Torneo <select name="idt">
<?php
	$selt = mysql_query("SELECT Id, Nome FROM Tornei ORDER BY Id DESC");
	while($t = mysql_fetch_array($selt)){
		echo "<option value=\"".$t['Id']."\">[".$t['Id'] . "] - " .$t['Nome']."</option>";
	}
?>
</select><br /><br />
<input type="submit" value="Seleziona torneo" />
</form>

<?php
		} else{
		
		$idt =$_GET['idt'];
		
		$selsq = mysql_query("SELECT Id,Nome FROM Squadre WHERE Torneo='$idt'");
		
		$sq = array(array());
		$i = 0;
		while ($s = mysql_fetch_array($selsq)){
			$sq["$i"]['Nome'] = $s['Nome'];
			$sq["$i"]['Id'] = $s['Id'];
			$i++;
		}
		
?>
<form action="?todo=programmazione&amp;idt=<?php echo $idt; ?>" method="post">
Squadra in casa <select name="squadraincasa">
<?php for($k=0; $k<$i; $k++) echo "<option value=\"".$sq["$k"]['Id']."\">".$sq["$k"]['Nome']."</option>"; ?>
</select><br /><br />
Squadra fuori casa <select name="squadrafuoricasa">
<?php for($k=0; $k<$i; $k++) echo "<option value=\"".$sq["$k"]['Id']."\">".$sq["$k"]['Nome']."</option>"; ?>
</select><br /><br />
Campo <select name="campo">
<?php
	$selc = mysql_query("SELECT Id, NomeUF FROM Campi");
	while($c = mysql_fetch_array($selc)){
		echo "<option value=\"".$c['Id']."\">".$c['NomeUF']."</option>";
	}
?>
</select><br /><br />
Arbitro <input type="text" name="arbitro" /><br /><br />
<?php echo date("Y"); ?> / <input type="text" name="mm" value="mm" size="3" /> /
 <input type="text" name="gg" size="3" value="gg" /><br /><br />
Ora (08-20) <input type="text" name="hh" size="4" /><br /><br />
Durata <select name="durata">
<option value="1">1</option><option value="2">2</option><option value="3">3</option><option value="4">4</option>
</select><br /><br />
<input type="submit" name="send" value="Aggiungi partita!" />
</form>

<?php
		
		if(isset($_POST['send'])){ // ADD Partite
			$sic = $_POST['squadraincasa'];
			$sfc = $_POST['squadrafuoricasa'];
			$idc = $_POST['campo'];
			$arb = $_POST['arbitro'];
			$doi = date("Y") . "-" .$_POST['mm'] . "-". $_POST['gg'] ." ". $_POST['hh'];
			$durata = $_POST['durata'];
			$hfine = $_POST['hh']+$durata;
			$dof = date("Y") . "-".  $_POST['mm'] . "-" . $_POST['gg'] . " " . (strlen($hfine)==1 ? "0".$hfine: $hfine);
		
			if ( mysql_query("INSERT INTO Partite(Id, DataOraInizio, DataOraFine, Durata, Risultato, Note, 
			Arbitro, Campo, SquadraInCasa, SquadraFuoriCasa) VALUES(NULL, '$doi', '$dof', '$durata', '', '',
			'$arb', '$idc', '$sic', '$sfc')") ){
				echo "Partita aggiunta correttamente";
			}
			else{
				echo "<p>Si è verificato un errore: " . mysql_error() . "</p>";
			}
		}
	
		}
	} // todo = programmazione
	else if($_GET['todo']=="calendario"){
		
		$mm = date("n")-1;
		if(isset($_GET['m'])) $mm = $_GET['m'];
		
		$mesi = array("gennaio", "febbraio", "marzo", "aprile", "maggio", "giugno", "luglio", "agosto",
		"settembre", "ottobre", "novembre", "dicembre");
		
		echo "Seleziona mese: ";
		for($i=0; $i<count($mesi); $i++){
			echo "<a href=\"?todo=calendario&amp;m=$i\">".substr($mesi[$i], 0, 3)."</a>; ";
		}
		$gg = array("31", "28", "31", "30", "31", "30", "31", "31", "30", "31", "30", "31");
		
		echo "<h4>".$mesi[$mm]."</h4> (M = Partita, P = Prenotazione)<table width=\"100%\">";
		$ng = $gg[$mm];
		echo "<tr><td></td>";
		for($i=1; $i<=$ng;$i++){
			$a = $i;
			if(strlen($i)==1)
				$a = "0".$a;
			$style = " style=\"background-color:" . ($i%2==1 ? "#FFCA57" : "#ededed") . "\" ";
			echo "<td $style><b>$a</b></td>";
		}
		echo "</tr>";
		
		$mm2 = strlen($mm)==1 ? "0".($mm+1) : "".($mm+1);
		$ym = date("Y") . $mm2;
		
		$sqlq = "SELECT Id, DATE_FORMAT(DataOraInizio, '%d') AS GG, DATE_FORMAT(DataOraInizio,'%H') AS HH, Campo, Durata, \"Prenotazione\" AS Tipo
FROM Prenotazioni_utente
WHERE DATE_FORMAT(DataOraInizio, '%Y%m')='$ym' AND COnfermata='1'
UNION
SELECT Id, DATE_FORMAT(DataOraInizio, '%d') AS GG, DATE_FORMAT(DataOraInizio,'%H') AS HH, Campo,Durata,  \"Partita\" AS Tipo
FROM Partite
WHERE DATE_FORMAT(DataOraInizio, '%Y%m')='$ym'";

		//echo "<pre>$sqlq</pre>";
		$pgg = array();
		$qs = mysql_query($sqlq);
		while($r=mysql_fetch_array($qs)){
			if( substr($r['Tipo'],0,2) == "Pa" ){
				$val = "M";
			}
			else
				$val =  "P";
			$x = $r['GG'];
			$y = $r['HH'];
			//$pgg[$x][$y] = "C=(".$r['Campo']."), ".substr($r['Tipo'],0,2).'('.$r['Id'].')';
			$pgg[$x][$y] = $val;
			for($k=1; $k<$r['Durata']; $k++){
				$y++;
				$y = strlen($y)==1 ? "0".$y : $y;
				$pgg[$x][$y] = $val;
			}
		}
		
		for($a=8; $a<=24; $a++){
			if($a==24) $a = "00";
			
			echo "<tr class=\"rigo\"><td>Ore <b>$a</b></td>";
			$a2 = strlen($a)==1 ? "0".$a : $a;
			for($b=1; $b<=$ng; $b++){
				$b2 = strlen($b)==1 ? "0".$b : $b;
				if(isset($pgg[$b2][$a2]))
					echo "<td>".$pgg[$b2][$a2]."</td>";
				else
					echo "<td>&nbsp;</td>";
			}
			echo "</tr>";
			
			if($a=="00") break;
		}
		
		echo "</table>";
		
	
	}
	// NON SO
	else {
		echo "<p>Operazione non specificata.</p>";
	}
	
	mysql_close($cn);
}

} // session not set
?>

</body>

</html>