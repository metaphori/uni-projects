package asw1022.model.dixit;

/**
 * The game phases in Dixit.
 * @author Roberto Casadei <roberto.casadei12@studio.unibo.it>
 */
public enum GamePhase {
    
    Setup("setup"),
    SetPhrase("setPhrase"),
    SelectCard("selectCard"),
    Results("results"),
    Vote("vote"),
    End("end");
    
    private final String text;

    /**
     * @param text
     */
    private GamePhase(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return this.text;
    }    
    
}
