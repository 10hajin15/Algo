class Solution {
    public String solution(String new_id) {
        String answer = new_id.toLowerCase();
        answer = answer.replaceAll("[^-_.a-z0-9]", "");
        answer = answer.replaceAll("\\.+", ".");
        answer = answer.replaceAll("^[.]|[.]$", "");
        if(answer.isEmpty()) answer = "a";
        if(answer.length() >= 16) {
            answer = answer.substring(0, 15);
            answer = answer.replaceAll("[.]$", "");
        }

        while(answer.length() < 3) {
            answer += answer.charAt(answer.length()-1);
        }

        System.out.println(answer);
        return answer;
    }
}