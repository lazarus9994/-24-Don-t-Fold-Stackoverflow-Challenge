public enum PokerHandRank {
    ROYAL_FLUSH(512),
    STRAIGHT_FLUSH(256),
    FOUR_OF_A_KIND(128),
    FULL_HOUSE(64),
    FLUSH(32),
    STRAIGHT(16),
    THREE_OF_A_KIND(8),
    TWO_PAIR(4),
    ONE_PAIR(2),
    HIGH_CARD(1);

    private final int value;

    PokerHandRank(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
