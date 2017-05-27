package asw1022.model.dixit;

/**
 * The actions a player can do in Dixit.
 * @author Roberto Casadei <roberto.casadei12@studio.unibo.it>
 */
public enum GameAction {
    
    JoinMatch("join"),
    GivePhrase("phrase"),
    SelectCard("select"),
    VoteCard("vote"),
    Ok("ok"),
    None("none");
    
    private final String text;

    /**
     * @param text
     */
    private GameAction(final String text) {
        this.text = text;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }

}
