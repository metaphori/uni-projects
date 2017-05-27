package asw1022.model.dixit;

import asw1022.util.Utils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * This class keeps the state of a single game execution (a match).
 * @author Roberto Casadei <roberto.casadei12@studio.unibo.it>
 */
public class GameExecution {
    
    protected final String name;
    protected String title;
    protected List<Card> allcards;
    protected LinkedList<Card> deck;
    protected List<Player> players;
    protected Map<Player, List<Card>> cardsPerPlayer;
    protected Map<Player, Integer> pointsPerPlayer;
    protected Map<Player, Card> selectedCardForPlayer;
    protected Map<Player, Player> votes;
    protected final MatchConfiguration matchConfig;
    protected GamePhase phase;
    protected Player turn;
    protected int numCardsPerPlayer;
    protected String phrase;
    protected int pointLimit = 3;
    protected Player winner = null;
    protected List<Player> pIsOk = new ArrayList<Player>();
    
    public GameExecution(MatchConfiguration match, 
            List<Card> allcards, 
            Player first) throws GameException{
        this.name = GenerateUniqueName();
        this.matchConfig = match;
        this.allcards = allcards;
        this.deck = new LinkedList<Card>(Utils.Shuffle(allcards));
        this.players = new ArrayList<Player>();
        this.phase = GamePhase.Setup;
        this.numCardsPerPlayer = match.getNumCardsForPlayers();
        this.pointLimit = match.getNumPoints();
        this.cardsPerPlayer = new HashMap<Player, List<Card>>();
        this.pointsPerPlayer = new HashMap<Player, Integer>();
        this.selectedCardForPlayer = new HashMap<Player, Card>();
        this.votes = new HashMap<Player, Player>();
        this.phrase = null;
        this.title = "Untitled-"+this.name;
        this.addPlayer(first);
    } 
    
    /**
     * It generates a unique match name.
     * @return 
     */
    protected String GenerateUniqueName(){
        Calendar cal = Calendar.getInstance();
        Long timems = cal.getTimeInMillis();
        return "m"+timems.hashCode();
    }
    
    /**
     * 
     * @return the (unique) name of the match
     */
    public String getName(){
        return this.name;
    }
    
    /**
     * 
     * @return the title of the match
     */
    public String getTitle(){
        return this.title;
    }
    
    /**
     * It sets the title of the match.
     * @param title 
     */
    public void setTitle(String title){
        this.title = title;
    }
    
    /**
     * 
     * @return the match configuration
     */
    public MatchConfiguration getMatchConfiguration(){
        return this.matchConfig;
    }
    
    /************************************/
    /* PHASE: Setup */
    /************************************/     
    
    /**
     * Adds a player to the match.
     * <p>
     * When all the players have joined the match,
     * the game starts and the moves to the "setPhrase" phase.
     * @param p The player.
     * @throws GameException if the request is made in the wrong phase or if 
     * no more players can be added.
     */
    public synchronized void addPlayer(Player p) throws GameException {
        if(this.phase != GamePhase.Setup)
            throw new GameException("Wrong phase. Trying to add player but phase is " + this.phase);
        
        if(!waitingForPlayers())
            throw new GameException("This match cannot accept any more players.");
        players.add(p);
        
        // then, assign cards
        Random rand = new Random();
        List<Card> cards = new ArrayList<Card>();
        for(int i=0; i<this.numCardsPerPlayer; i++){
            cards.add(deck.removeFirst());
        }
        cardsPerPlayer.put(p, cards);
        pointsPerPlayer.put(p, 0);
        
        if(!waitingForPlayers()){
            this.phase = GamePhase.SetPhrase;
            this.turn = players.get(0);
        }
    }
    

    /************************************/
    /* PHASE: Set Phrase */
    /************************************/      
    
    /**
     * It allows the player that owns the turn to provide the clue.
     * <p>
     * When the player has given his/her clue, the game moves
     * to the "select" phase.
     * @param p The player.
     * @param phrase The clue.
     * @throws GameException if the request is made in the wrong phase or 
     * if the player that made the request does not own the turn.
     */
    public synchronized void setPhrase(Player p, String phrase) throws GameException{
        if(this.phase != GamePhase.SetPhrase)
            throw new GameException("Wrong phase. Trying to set phrase but phase is " + this.phase);        
        if(!p.equals(this.turn))
            throw new GameException("The phrase can be set only by the player that owns the turn.");
        this.phrase = phrase;
        this.phase = GamePhase.SelectCard;
    }
    
    /************************************/
    /* PHASE: Select Card */
    /************************************/     
    
    /**
     * It allows a player to select the card identified by cardName argument.
     * <p>
     * When all the players have selected their card, the game
     * moves to the "Vote" phase.
     * @param p The player.
     * @param cardName The (unique) name of the card.
     * @throws GameException if the request is made in the wrong phase, if the player has
     *  already selected a card, or if the card does not belong to the player.
     */
    public synchronized void selectCard(Player p, String cardName) throws GameException{
        if(this.phase != GamePhase.SelectCard)
            throw new GameException("Wrong phase. Trying to select card but phase is " + this.phase);
        
        Card previous = selectedCardForPlayer.get(p);
        if(previous!=null)
            throw new GameException("The player " + p.getName() + " has already chosen a card.");
        
        List<Card> playerCards = this.cardsPerPlayer.get(p);
        
        Card selected = null;
        for(Card c : playerCards){
            if(c.getName().equals(cardName)){
                selected = c;
                break;
            }
        }
        
        if(selected==null)
            throw new GameException("The selected card does not belong to the player.");
        
        // Let's remove the card
        playerCards.remove(selected);
        deck.addLast(selected);
        pickCardFromDeck(p);
        
        this.selectedCardForPlayer.put(p, selected);
        
        System.out.println(this.selectedCardForPlayer.size() + " out of " + this.players.size());
        
        // If all the players have chosen a card, advance the game
        if(this.selectedCardForPlayer.size() == this.players.size()){
            this.phase = GamePhase.Vote;
        }
    }    

    /**
     * It allows the player p to pick the card at the top of the deck,
     * @param p The player.
     */
    public synchronized void pickCardFromDeck(Player p){
        Card c = deck.removeFirst();
        this.cardsPerPlayer.get(p).add(c);
    }
    
    /************************************/
    /* PHASE: Vote */
    /************************************/     
    
    /**
     * It allows a player to vote the card identified by the cardName argument.
     * <p>
     * When all the players (except the player that owns the turn) have voted,
     * the game moves to the "Results" phase or to the "End" phase if any
     * player has reached to max number of points.
     * @param voter The player.
     * @param cardName The (unique) name of the card.
     * @throws GameException if the request is made in the wrong phase, if the player has
     * already voted, or if the player is trying to vote the card he/she has selected previously.
     */
    public synchronized void vote(Player voter, String cardName) throws GameException{
        if(this.phase != GamePhase.Vote)
            throw new GameException("Wrong phase. Trying to vote but phase is " + this.phase);
        
        if(this.votes.containsKey(voter))
            throw new GameException("Player " + voter.getName() + " has already voted.");
        
        Player votedByCard = null;
        for(Player p : this.selectedCardForPlayer.keySet()){
            Card c = this.selectedCardForPlayer.get(p);
            if(c.getName().equals(cardName)){
                if(p.equals(voter))
                    throw new GameException("Player " + voter.getName() + " is trying to vote "
                            + "his own card.");
                votedByCard = p;
                break;
            }
        }
        
        if(votedByCard == null)
            throw new GameException("Player " + voter.getName() + 
                    " is voting a card which is not in the table.");
        
        this.votes.put(voter, votedByCard);
        
        if(this.votes.size() == (this.players.size()-1)){
            // 1) Calculate points
            CalculatePoints();
            
            // 2) Check if end
            for(Player p : pointsPerPlayer.keySet()){
                if(pointsPerPlayer.get(p) >= getPointLimit()){
                    this.phase = GamePhase.End;
                    this.winner = p;
                    return;
                }
            }
            
            // 3) Next phase
            this.phase = GamePhase.Results;
        }
    }
    
    /**
     * It updates the results for the current round.
     * <p>
     * The player that owns the turn receives 3 points if at least one player (but not 
     * all the players) has voted his/her card. The players that voted that card 
     * are given 3 points as well. Moreover, each player gets 1 point for each vote
     * has been assigned to his/her card.
     * Otherwise, if all or none of the players have voted
     * the card of the player that owns the turn, 
     * the latter receives no points (0), and all the players receive 2 points.
     */
    protected synchronized void CalculatePoints(){
        int nVotesTurn = 0;
        List<Player> votersOfTurn = new ArrayList<Player>();
        for(Player voter : votes.keySet()){
            Player voted = votes.get(voter);
            
            if(voted==turn){
                nVotesTurn++;
                votersOfTurn.add(voter);
            }
        }
        
        if(nVotesTurn>0 && nVotesTurn<(this.players.size()-1)){
            // The player of current turn takes 3 points as well as those who voted him
            int npTurn = this.pointsPerPlayer.get(turn);
            this.pointsPerPlayer.put(turn, npTurn+3);
            for(Player p : votersOfTurn){
                int npVoter = this.pointsPerPlayer.get(p);
                this.pointsPerPlayer.put(p, npVoter+3);
            }
            
            // Moreover, each players get a point for each vote
            for(Player voter : votes.keySet()){
                Player voted = votes.get(voter);
                // one cannot vote himself, and are not counted for the player in turn
                if(voted==voter || voted==this.turn) continue;
                
                int np = this.pointsPerPlayer.get(voted);
                this.pointsPerPlayer.put(voted, np+1);
            }            
        } else{
            // The player of current turn takes 0 points, all the other players 2 points
            for(Player p : this.players){
                if(p==turn) continue;
                int npVoter = this.pointsPerPlayer.get(p);
                this.pointsPerPlayer.put(p, npVoter+2);                
            }
        }
    }
        
    /************************************/
    /* PHASE: Results */
    /************************************/
    
    /**
     * It allows a player to signal his/her intention to proceed to the next round.
     * <p>
     * When all the players have notified their intention to proceed,
     * the turn goes to the next player and a new round starts.
     * @param player The player.
     * @throws GameException if the request is made in the wrong phase.
     */
    public synchronized void proceed(Player player) throws GameException {
        if(this.phase != GamePhase.Results)
            throw new GameException("Wrong phase. You are ok but phase is " + this.phase);
        if(pIsOk.contains(player)){
            //throw new GameException("You should not say you are ok twice.");
            return;
        }
        
        pIsOk.add(player);
        if(pIsOk.size()==this.players.size()){
            this.nextTurn();
        }
    }
    
    /**
     * It determines the next player of turn and the game moves to the "setPhrase" phase.
     * @return the next player that owns the turn.
     * @throws GameException if the request is made in the wrong phase.
     */
    protected synchronized Player nextTurn() throws GameException {
        if(this.phase != GamePhase.Results)
            throw new GameException("Wrong phase. Trying to next turn but phase is " + this.phase);        
        
        int index = players.indexOf(this.turn);
        if(index==(players.size()-1))
            index = 0;
        else
            index++;
        
        this.turn = players.get(index);
        this.phase = GamePhase.SetPhrase;
        
        // Cleanup
        this.phrase = "";
        this.selectedCardForPlayer.clear();
        this.votes.clear();
        this.pIsOk.clear();
        
        return turn;
    }
    
    /************************************/
    /* Accessors */
    /************************************/
    
    public synchronized Player getWinner(){
        return this.winner;
    }
    
    public synchronized int getPointLimit(){
        return this.pointLimit;
    }
    
    public synchronized GamePhase getPhase(){
        return this.phase;
    }
    
    public synchronized String getPhrase(){
        return this.phrase;
    }
    
    public synchronized Player getTurn(){
        return turn;
    }    
    
    public synchronized List<Card> getCardsForPlayer(String user){
        Player p = getPlayerByName(user);
        return cardsPerPlayer.get(p);
    }
    
    public synchronized int getPointsPerPlayer(String user){
        Player p = getPlayerByName(user);
        return pointsPerPlayer.get(p);        
    }

    public synchronized Player getPlayerByName(String user) {
        for(Player p : players){
            if(p.getName().equals(user))
                return p;
        }
        return null;
    }
    
    public synchronized int getMatchPlayerNum(){
        return matchConfig.getNumPlayers();
    }
    
    public synchronized List<Player> getPlayers(){
        // let's return a copy of the list
        return new ArrayList<Player>(this.players);
    }    
    
    public synchronized Card getCardByName(String cname){
        for(Card c : this.allcards){
            if(c.getName().equals(cname))
                return c;
        }
        return null;
    }
    
    public synchronized List<Card> getSelectedCardsRandomly(){
        List<Card> result = new ArrayList<Card>();
        List<Player> ps = new ArrayList<Player>(this.players);
        Random rand = new Random();
        while(ps.size()>0){
            int sz = ps.size();
            Player p = ps.remove(rand.nextInt(sz));
            Card c = selectedCardForPlayer.get(p);
            result.add(c);
        }
        
        return result;
    }
    
    public synchronized Map<Player,Card> getCardSelection() {
        return this.selectedCardForPlayer;
    }
    
    /**
     * 
     * @return a map from players to the card they have voted.
     */
    public synchronized Map<Player,Card> getVotes() {
        Map<Player,Card> result = new HashMap<Player,Card> ();
                
        for(Player voter : votes.keySet()){
            Player voted = votes.get(voter);
            Card cardVoted = selectedCardForPlayer.get(voted);
            result.put(voter, cardVoted);
        }
        
        return result;
    }
    
    /************************************/
    /* Utilities */
    /************************************/
    
    public synchronized boolean waitingForPlayers(){
        return players.size() < matchConfig.getNumPlayers();
    }    
    
}
