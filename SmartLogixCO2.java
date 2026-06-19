class FenwickTree {
    int[] bit;
    int n;

    FenwickTree(int n) {
        this.n = n;
        bit = new int[n + 1];
    }

    void update(int i, int val) {
        while (i <= n) {
            bit[i] += val;
            i += i & -i;
        }
    }

    int query(int i) {
        int sum = 0;
        while (i > 0) {
            sum += bit[i];
            i -= i & -i;
        }
        return sum;
    }
}

class SegmentTree {
    int[] tree;
    int n;

    SegmentTree(int[] arr) {
        n = arr.length;
        tree = new int[4 * n];
        build(arr, 0, 0, n - 1);
    }

    void build(int[] arr, int node, int l, int r) {
        if (l == r) {
            tree[node] = arr[l];
            return;
        }

        int mid = (l + r) / 2;

        build(arr, 2 * node + 1, l, mid);
        build(arr, 2 * node + 2, mid + 1, r);

        tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
    }

    int query(int node, int l, int r, int ql, int qr) {
        if (qr < l || ql > r) return 0;

        if (ql <= l && r <= qr) return tree[node];

        int mid = (l + r) / 2;

        return query(2 * node + 1, l, mid, ql, qr)
             + query(2 * node + 2, mid + 1, r, ql, qr);
    }
}

public class SmartLogixCO2 {
    public static void main(String[] args) {

        int[] sales = {10, 20, 30, 40, 50};

        // ---------------- Fenwick Tree ----------------
        FenwickTree ft = new FenwickTree(sales.length);

        for (int i = 0; i < sales.length; i++) {
            ft.update(i + 1, sales[i]);
        }

        System.out.println("Fenwick Prefix Sum (first 3 days): " + ft.query(3));

        // ---------------- Segment Tree ----------------
        SegmentTree st = new SegmentTree(sales);

        System.out.println("Segment Tree Range Sum (1 to 4): "
                + st.query(0, 0, sales.length - 1, 1, 4));
    }
}