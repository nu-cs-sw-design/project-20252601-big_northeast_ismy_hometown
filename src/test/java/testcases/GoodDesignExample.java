package testcases;

import java.util.List;

public class GoodDesignExample {
    private final List<String> items;

    public GoodDesignExample(List<String> items) {
        this.items = items;
    }

    public List<String> getItems() {
        return items;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof GoodDesignExample)) return false;
        GoodDesignExample other = (GoodDesignExample) obj;
        return items != null ? items.equals(other.items) : other.items == null;
    }

    @Override
    public int hashCode() {
        return items != null ? items.hashCode() : 0;
    }
}



