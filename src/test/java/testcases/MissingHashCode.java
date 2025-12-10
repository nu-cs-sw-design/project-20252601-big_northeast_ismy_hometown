package testcases;

public class MissingHashCode {
    private int value;
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof MissingHashCode)) return false;
        MissingHashCode other = (MissingHashCode) obj;
        return value == other.value;
    }
    
}



