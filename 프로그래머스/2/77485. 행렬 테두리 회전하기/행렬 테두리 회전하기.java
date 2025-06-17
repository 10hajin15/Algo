class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[][] arr = new int[rows][columns];
        for(int i=0; i<rows; i++) {
            for(int j=0; j<columns; j++) {
                arr[i][j] = (i*columns) + (j+1);
            }
        }
        
        int[] answer = new int[queries.length];
        int idx = 0;
        for(int[] query: queries) {
            int si = query[0]-1;
            int sj = query[1]-1;
            int ei = query[2]-1;
            int ej = query[3]-1;
            
            
            int start = arr[si][sj];
            int min = start;
            
            for(int i=si; i<ei; i++) {
                arr[i][sj] = arr[i+1][sj];
                if(arr[i][sj] < min) min = arr[i][sj];
            }
            
            for(int j=sj; j<ej; j++) {
                arr[ei][j] = arr[ei][j+1];
                if(arr[ei][j] < min) min = arr[ei][j];
            }
            
            for(int i=ei; i>si; i--) {
                arr[i][ej] = arr[i-1][ej];
                if(arr[i][ej] < min) min = arr[i][ej];
            }
            
            for(int j=ej; j>sj+1; j--) {
                arr[si][j] = arr[si][j-1];
                if(arr[si][j] < min) min = arr[si][j];
            }
            
            arr[si][sj+1] = start;
            
            answer[idx++] = min;
        }
        
        return answer;
    }
}