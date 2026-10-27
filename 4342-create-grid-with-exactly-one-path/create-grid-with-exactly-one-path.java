class Solution {
    public String[] createGrid(int m, int n) {
        String[] grid = new String[m];
        for (int i = 0; i < m; i++) {
            char[] row = new char[n];



            for (int j = 0; j < n; j++) {
                row[j] = '#';
            }
            grid[i] = new String(row);
        }

        for (int j = 0; j < n; j++) {
            grid[0] = grid[0].substring(0, j) + "." + grid[0].substring(j + 1);
        }

        for (int i = 1; i < m; i++) {
            char[] row = grid[i].toCharArray();
            row[n - 1] = '.';
            grid[i] = new String(row);
        }
return grid;
    }
}