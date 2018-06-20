import java.util.ArrayList;

//Se dă un vector a=(a1,…an) de tip munte (există un indice i astfel încât a1<a2<…<ai > ai+1>…>an; ai se numește vârful muntelui)

public class Peek {

    private Integer selectedItem;
    private Integer selectedIndex;

    public void setElements(ArrayList<Integer> elements) {
        dei(elements, 0, elements.size() - 1);
    }

    private void dei(ArrayList<Integer> elements, int st, int dr) {
        int m = (st + dr) / 2;
        int e1 = elements.get(m - 1);
        int e2 = elements.get(m);
        int e3 = elements.get(m + 1);
        if (e1 < e2 && e2 > e3) {
            selectedIndex = m;
            selectedItem = e2;
            return;
        }
        if (e1 < e2 && e2 < e3) {
            dei(elements, m, dr);
        } else if (e1 > e2 && e2 > e3) {
            dei(elements, st, m);
        }
    }

    public Integer select() {
        if (selectedItem != null) {
            Integer newSelected = selectedItem;
            selectedItem = null;
            return newSelected;
        }

        Integer newSelected = selectedIndex;
        selectedIndex = null;
        return newSelected;
    }

    public boolean canSelect() {
        return false;
    }

    public String toString() {
        return "Divide et Impera Strategy";
    }
}
