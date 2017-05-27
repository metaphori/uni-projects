<?php
session_start();

?>

<html>

<head>

<link rel="stylesheet" type="text/css" href="style.css" />

</head>
<body>

<div id="top">
<h1>SportCore</h1>
</div>

<div id="body">

<div id="sidebar">
	<h2>Navigazione</h2>
	<ul>
		<li><a href="?what=campi">Lista campi disponibili</a></li>
		<li><a href="?what=tornei">Iscrizioni ai nuovi tornei</a></li>
	</ul>
	
	<h2>Pannello utente</h2>
	<?php
	$cn = mysql_connect("localhost","root","enter");
	mysql_select_db("sportcore", $cn);
	
	if(isset($_SESSION['user'])){
	
	$selu = mysql_query("SELECT Nome FROM Utenti WHERE Id='". $_SESSION['user'] ."'");
	$user = mysql_fetch_array($selu);
?>
Benvenuto, <?php echo $user['Nome']; ?>.<br />
<ul>
	<li><a href="?what=prenotazioniutente">Prenotazioni</a></li>
	<li><a href="?what=squadreutente">Squadre</a></li>
	<li><a href="?what=logout">Effettua il logout</a></li>
</ul>
<?php
	}
	else{
?>
	<form action="?what=login" method="post">
		<input type="text" name="username" value="username" /><br />
		<input type="password" name="password" value="password" />
		<input type="submit" value="Login" />
	</form>
<?php
	}
	?>
	
	<h2>Amministrazione</h2>
	<ul>
	<li><a href="admin.php">Area di amministrazione</a></li>
	</ul>
</div>
<div id="content">

<a href="index.php">Home</a><br />


<?php
if(isset($_GET['what'])){
	
if($_GET['what']=="campi"){
	echo "<h1>Lista campi disponibili</h1>";
	$query = mysql_query("SELECT * FROM Campi");
	$n = mysql_num_rows($query);
	echo "<p>Ci sono $n campi disponibili.</p>";
	$i=0;
	while($r = mysql_fetch_array($query)){
		$i++;
		echo "<div class=\"" . ($i%2==0 ? 'pari':'dispari') ."\">
<h3>".$r["NomeUF"]."</h3>
<img src=\".". $r['ImgUrl'] . "\" /><br />
<p><i>" . str_replace("\n", '<br />', $r['Descrizione']) . "</i></p>
<p>Tariffa oraria: ".$r["TariffaOraria"]."</p>
<p><a href=\"?what=prenota&idcampo=".$r["Id"]."\">Prenota questo campo</a></p></div>";
	}

} // $_GET['what']=="campi"
else if($_GET['what']=="prenota" ){
	$idc = $_GET['idcampo'];
	
	$query = @mysql_query("SELECT * FROM Campi Where Id='$idc'");
	$n = mysql_num_rows($query);
	if($n==1){
		$r = @mysql_fetch_array($query);
		echo "<h3>Prenotazione " . $r['NomeUF'] ."</h3>";
?>
<form action="?what=prenota&amp;idcampo=<?php echo $idc; ?>" method="post">
<fieldset><legend>Dati prenotazione</legend>
<?php echo date("Y"); ?> / <input type="text" name="mm" value="<?php echo isset($_POST['mm'])?$_POST['mm']:"mm"; ?>" size="3" /> /
 <input type="text" name="gg" value="<?php echo isset($_POST['gg'])?$_POST['gg']:"gg"; ?>" size="3" /><br /><br />
Ora (08-20) <input type="text" name="hh" value="<?php echo isset($_POST['hh'])?$_POST['hh']:"hh"; ?>" size="4" /><br /><br />
Durata <select name="durata">
<option value="1" <?php if(isset($_POST['durata']) && $_POST['durata']=="1") echo "selected=\"true\"";?>>1</option>
<option value="2" <?php if(isset($_POST['durata']) && $_POST['durata']=="2") echo "selected=\"true\"";?>>2</option>
<option value="3" <?php if(isset($_POST['durata']) && $_POST['durata']=="3") echo "selected=\"true\"";?>>3</option>
<option value="4" <?php if(isset($_POST['durata']) && $_POST['durata']=="4") echo "selected=\"true\"";?>>4</option>
</select><br /><br />
<input type="submit" name="send" value="Prenota" />
</fieldset>
</form>
<?php
	if(isset($_POST['send'])){
		$dataorainizio = date("Y") . $_POST['mm'] . $_POST['gg'] . $_POST['hh'] . "00";
		$durata = $_POST['durata'];
		$dataorafine = date("Y") . $_POST['mm'] . $_POST['gg'] . ((int)$_POST['hh']+(int)$_POST['durata']) ."00";

		$sqlq = "SELECT DataOraInizio, DataOraFine, Campo
FROM Prenotazioni_utente
WHERE DATE_FORMAT(DataOraFine, '%Y%m%d%H%s') > '$dataorainizio' AND DATE_FORMAT(DataOraInizio, '%Y%m%d%H%s') < '$dataorafine' AND Campo = '$idc'

UNION

SELECT DataOraInizio, DataOraFine, Campo
FROM Partite
WHERE DATE_FORMAT(DataOraFine, '%Y%m%d%H%s') > '$dataorainizio' AND DATE_FORMAT(DataOraInizio, '%Y%m%d%H%s') < '$dataorafine' AND Campo = '$idc'";

		//echo "<pre>$sqlq</pre>";
		
		$query = mysql_query($sqlq) or die(mysql_error());

		if(mysql_num_rows($query)>0){
			/*
			while($r = mysql_fetch_array($query)){
				echo $r['DataOraInizio'] . "; " . $r['DataOraFine'] . "; " . $r['Campo'];
			}
			*/
			echo "<p class=\"err\">Il campo è già stato prenotato per la data e la durata prescelta.</p>";
		}
		else{
			if(isset($_SESSION['user'])){
				$dataorainizio = date("Y") ."-". $_POST['mm'] ."-". $_POST['gg'] . " " . $_POST['hh'] . ":00";
				$orafine = ((int)$_POST['hh']+(int)$_POST['durata']);
				if($orafine=="24") $orafine = "00";
				$dataorafine = date("Y") ."-". $_POST['mm'] ."-". $_POST['gg'] ." ". $orafine .":00";
	
				$insert = "INSERT INTO Prenotazioni_Utente(Id, DataOraInizio, DataOraFine, Durata, Campo, Confermata, Utente)
				VALUES(NULL, '$dataorainizio', '$dataorafine', '$durata', '$idc', '0', '".$_SESSION['user']."')";
				//echo "<pre>$insert</pre>";
				$query = mysql_query($insert) or die(mysql_error());
				
				if($query){
					echo "<p><b>La prenotazione è stata inoltrata.</b> Ora deve attendere conferma da parte della segreteria.</br />
					Grazie per aver usufruito del nostro servizio.</p>";
				}
				else{
					echo "<p class=\"err\">Si è verificato un problema durante la registrazione della prenotazione.</>";
				}
			}
			else{
				echo "<p class=\"err\">Prima di effettuare prenotazioni devi effettuare il login.</>";
			}
		}
	
	}
}
} // $_GET['what']=="prenota"
else if($_GET['what']=="tornei"){
	echo "<h3>Tornei attivati</h3>";
	
	$now = date("YmdHs");
	
	$query = "SELECT *, DATE_FORMAT(AperturaIscrizioni, '%d/%m/%Y alle ore %H') AS AI,
	DATE_FORMAT(ChiusuraIscrizioni, '%d/%m/%Y alle ore %H') AS CI FROM Tornei 
	WHERE DATE_FORMAT(AperturaIscrizioni, '%Y%m%d%H%s')< '$now'
			AND DATE_FORMAT(ChiusuraIscrizioni, '%Y%m%d%H%s')>'$now'";
	// echo "<pre>$query</pre>";
	$selt = mysql_query($query) or die(mysql_error());
	if(mysql_num_rows($selt)>0){
		while($r = mysql_fetch_array($selt)){
		
			echo "<div class=\"torneo\">";
			echo "<h3>".$r['Nome']."</h3>";
			echo "<p>Apertura iscrizioni: ".$r['AI'].".<br />Chiusura iscrizioni: ".$r['CI'].".<br />
			Tassa iscrizione: ".$r['TassaIscrizione']." €.<br />Premio finale: ".$r['Premio']." €.</p>";
			
			$seln = mysql_query("SELECT COUNT(*) AS C FROM Squadre WHERE Torneo='".$r['Id']."'");
			$num = mysql_fetch_array($seln);
			$num = $num['C'];
			$ndisp = $r['MaxNumIscritti']-$num;
			echo "<p>Numero posti disponibili: " . $ndisp .".</p>";
			
			if($ndisp>0){
				echo "<a href=\"?what=iscrivi&idt=".$r['Id']."\">Iscrivi una squadra a questo torneo!</a>";
			}
			
			echo "</div>";
		}
	}
	else{
		echo "<p>Non ci sono tornei attivi in questo momento.</p>";
	}
	
} // $_GET['what']=="tornei"
else if($_GET['what']=="iscrivi"){
	if(isset($_SESSION['user'])){
	$idt = $_GET['idt'];
	$idu = $_SESSION['user'];
	
	$selt = mysql_query("SELECT * FROM Tornei WHERE Id='$idt'");
	$t = mysql_fetch_array($selt);
	
	echo "<h3>Iscrivi una squadra al torneo ".$t['Nome']."</h3>";
?>
	<form action="?what=iscrivi&amp;idt=<?php echo $idt;?>" method="post">
		Nome squadra: <input type="text" name="nome" /><br /><br />
		<input type="submit" name="send" value="Registra squadra!" />
	</form>
<?php
		if(isset($_POST['send'])){
			$ns = $_POST['nome'];
			
			$maxnum = mysql_query("SELECT COUNT(*) AS NUM, MaxNumIscritti FROM Squadre, Tornei 
			WHERE Squadre.Torneo='$idt' AND Squadre.Torneo=Tornei.Id GROUP BY MaxNumIscritti");
			$maxnum = mysql_fetch_array($maxnum);
			if( $maxnum['NUM']==$maxnum['MaxNumIscritti'] ){
				echo "<p class=\"err\">Il torneo ha raggiunto la quota massima di iscrizioni.</p>";
			}
			else{	
				$check = mysql_query("SELECT Id FROM Squadre WHERE Torneo='$idt' AND Utente='$idu'");
				if(mysql_num_rows($check)==0){
					$insert = mysql_query("INSERT INTO Squadre(Id, Nome, Utente, Torneo)
					VALUES(NULL, '$ns', '$idu', '$idt')");
					if($insert)
						echo "<p>Squadra registrata correttamente al torneo.</p>";
					else
						echo "<p class=\"err\">Impossibile registrare la squadra al torneo.</p>";
				} else{
					echo "<p class=\"err\">Hai già registrato una squadra a questo torneo.</p>";
				}
			}
		}
	} else{
		echo "<p class=\"err\">Per iscrivere una squadra devi essere loggato.</p>";
	}
}
if($_GET['what']=="login"){
	echo "<h3>Login</h3>";
	$user = $_POST['username'];
	$pass = md5($_POST['password']);
	
	$selu = mysql_query("SELECT Id FROM Utenti WHERE Username='$user' AND Password='$pass'");
	if($selu && @mysql_num_rows($selu)==1){
		$r = mysql_fetch_array($selu);
		$_SESSION['user'] = $r['Id'];
		echo "<p>Login effettuato correttamente.</p>";
	}
	else {
		echo "<p class=\"err\">Impossibile effettuare il login. Username e password non coincidono.</p>";
	}
} // $_GET['what']=="login"
else if($_GET['what']=="logout"){
	session_destroy();
	$_SESSION['user']=null;
	echo "<p>Login effettuato correttamente.</p>";
}
else if($_GET['what']=="prenotazioniutente" && isset($_SESSION['user'])){
	echo "<h3>Prenotazioni</h3>";
	$now = date("YmdH");
	$selpre = mysql_query("SELECT *, DATE_FORMAT(DataOraInizio, '%d/%m/%Y alle ore %H') AS DI, C.NomeUF AS N 
	FROM Prenotazioni_Utente P, Campi C WHERE Utente='".$_SESSION['user']."' AND P.Campo=C.Id
	AND DATE_FORMAT(DataOraInizio, '%Y%m%d%H')>$now");
	if(mysql_num_rows($selpre)>0){
		echo "<ul>";
		while($r = mysql_fetch_array($selpre)){
			echo "<li>Su <b>".$r['N']. "</b>.<br />Data: <b>".$r['DI']."</b> per <b>".$r['Durata'] . "</b> ore.<br />
			Stato: " . ($r['Confermata']=="0"?"<span style=\"color:#f00;\">non confermata</span>":"confermata").".</li>";
		}
		echo "</ul>";
	}
	
} // $_GET['what']=="prenotazioniutente"
else if($_GET['what']=="squadreutente" && isset($_SESSION['user'])){
	echo "<h3>Squadre</h3>";
	
	$selsq = mysql_query("SELECT *, S.Nome AS NomeS, T.Nome AS NomeT, S.Id AS Id FROM Squadre S, Tornei T WHERE S.Torneo=T.Id
	AND Utente='".$_SESSION['user']."'");
	if(mysql_num_rows($selsq)>0){
		echo "<ul>";
		while ($r = mysql_fetch_array($selsq)){
			echo "<li><span style=\"font-family:verdana;\">".$r['NomeS']."</span> -
			Iscritta a <i style=\"font-family:verdana;\">".$r['NomeT']."</i><br /><b>Componenti</b>: ";
			
			$selg = mysql_query("SELECT Nome, Cognome, Numero FROM Giocatori G, Rosa R WHERE R.Squadra='".$r['Id']."'
				AND R.Giocatore = G.Id");
			while($player = mysql_fetch_array($selg)){
				echo $player['Nome'] . " " . $player['Cognome'] . " (".$player['Numero']."); &nbsp;";
			}
			
			echo "<br /><a href=\"?what=aggiungigiocatore&amp;ids=".$r['Id']."\">Aggiungi un componente alla squadra</a></li>";
		}
		echo "</ul>";
	}
	else{
		echo "<p>Nessuna squadra è stata iscritta ad alcun torneo.</p>";
	}

} // $_GET['what']=="squadreutente"
else if($_GET['what']=="aggiungigiocatore" && isset($_SESSION['user'])){
	$ids = $_GET['ids'];
	$check = mysql_query("SELECT * FROM Squadre WHERE Id='$ids' AND Utente='".$_SESSION['user']."'");
	echo "<h3>Aggiungi un nuovo componente alla squadra</h3>";
	if(@mysql_num_rows($check)==1){
?>
<form action="?what=aggiungigiocatore&amp;ids=<?php echo $ids; ?>" method ="post">
Codice fiscale <input type="text" name="cf" /> <br /><br />
Nome <input type="text" name="nome" /><br /><br />
Cognome <input type="text" name="cognome" /><br /><br />
Data di nascita <input type="text" name="dn" value="yyyy-mm-gg" /><br /><br />
Numero di maglia <select name="num">
<?php for($i=1; $i<18; $i++) echo "<option value=\"$i\">$i</option>"; ?>
</select><br /><br />
<input type="submit" value="Aggiungi componente!" name="addc" />
</form>

<?php

		if(isset($_POST['addc'])){
			
			$exists = mysql_query("SELECT Id FROM Giocatori WHERE CF='".$_POST['cf']."'");
			if(@mysql_num_rows($exists)==0){
				mysql_query("INSERT INTO Giocatori(Id, CF, Nome, Cognome, DataNascita)
				VALUES(NULL,'".$_POST['cf']."','".$_POST['nome']."','".$_POST['cognome']."','".$_POST['dn']."')");
				//or die(mysql_error());
			}
			
			$exists = mysql_query("SELECT Id FROM Giocatori WHERE CF='".$_POST['cf']."'");
			if(@mysql_num_rows($exists)==1){
				$g = mysql_fetch_array($exists);
				if ( mysql_query("INSERT INTO Rosa(Giocatore, Squadra, Numero)
				VALUES('".$g['Id']."','$ids','".$_POST['num']."')") ){
					echo "<p>Giocatore aggiunto correttamente alla squadra.</p>";
				}
				else{
					echo "<p class=\"err\">Si è verificato un errore nell'inserimento del giocatore alla squadra.</p>";
					//echo mysql_error();
				}
			
			} else{
				echo "<p class=\"err\">Si è verificato un errore nell'inserimento del giocatore.</p>";
			}
		}

	}
	else{
		echo "<p class=\"err\">Errore di sicurezza</p>";
	}
}
} // if(isset($_GET['what']))
else {
?>
<h2>SportCore</h2>
<p>Il nostro è un centro sportivo di nuova generazione.<br />
Il servizio consente la prenotazione dei campi da gioco e l'iscrizione ai tornei via web.<br />
Per poter usufruire del servizio web occorre disporre di un account utente, che può essere richiesto in
segreteria o telefonicamente.</p>
<ul class="clist">
	<li>Telefono: 0547-8318395</li>
	<li>Apertura prenotazioni: ore 08.00.</li>
	<li>Chiusura prenotazioni: ore 20.00.</li>
</ul>

<?php
}
?>

</div>
<div class="clear">
</div>
</div>

<div id="footer">
	<p><b>Roberto Casadei</b> - 0000440575 - Gruppo 288 - <u>Interfaccia Web per il progetto di Sistemi Informativi</u></p>
</div>


</body>