# [level 4] 호텔 방 배정 - 64063 

[문제 링크](https://school.programmers.co.kr/learn/courses/30/lessons/64063) 

### 성능 요약

메모리: 163 MB, 시간: 364.90 ms

### 구분

코딩테스트 연습 > 2019 카카오 개발자 겨울 인턴십

### 채점결과

정확성: 78.8<br/>효율성: 21.2<br/>합계: 100.0 / 100.0

### 제출 일자

2025년 07월 07일 23:40:01

### 문제 설명

<p><strong>[본 문제는 정확성과 효율성 테스트 각각 점수가 있는 문제입니다.]</strong></p>

<p>"스노우타운"에서 호텔을 운영하고 있는 "스카피"는 호텔에 투숙하려는 고객들에게 방을 배정하려 합니다. 호텔에는 방이 총 k개 있으며, 각각의 방은 1번부터 k번까지 번호로 구분하고 있습니다. 처음에는 모든 방이 비어 있으며 "스카피"는 다음과 같은 규칙에 따라 고객에게 방을 배정하려고 합니다.</p>

<ol>
<li>한 번에 한 명씩 신청한 순서대로 방을 배정합니다.</li>
<li>고객은 투숙하기 원하는 방 번호를 제출합니다.</li>
<li>고객이 원하는 방이 비어 있다면 즉시 배정합니다.</li>
<li>고객이 원하는 방이 이미 배정되어 있으면 원하는 방보다 번호가 크면서 비어있는 방 중 가장 번호가 작은 방을 배정합니다.</li>
</ol>

<p>예를 들어, 방이 총 10개이고, 고객들이 원하는 방 번호가 순서대로 [1, 3, 4, 1, 3, 1] 일 경우 다음과 같이 방을 배정받게 됩니다.</p>
<table class="table">
        <thead><tr>
<th>원하는 방 번호</th>
<th>배정된 방 번호</th>
</tr>
</thead>
        <tbody><tr>
<td>1</td>
<td>1</td>
</tr>
<tr>
<td>3</td>
<td>3</td>
</tr>
<tr>
<td>4</td>
<td>4</td>
</tr>
<tr>
<td>1</td>
<td>2</td>
</tr>
<tr>
<td>3</td>
<td>5</td>
</tr>
<tr>
<td>1</td>
<td>6</td>
</tr>
</tbody>
      </table>
<p>전체 방 개수 k와 고객들이 원하는 방 번호가 순서대로 들어있는 배열 room_number가 매개변수로 주어질 때, 각 고객에게 배정되는 방 번호를 순서대로 배열에 담아 return 하도록 solution 함수를 완성해주세요.</p>

<h4><strong>[제한사항]</strong></h4>

<ul>
<li>k는 1 이상 10<sup>12</sup> 이하인 자연수입니다.</li>
<li>room_number 배열의 크기는 1 이상 200,000 이하입니다.</li>
<li>room_number 배열 각 원소들의 값은 1 이상 k 이하인 자연수입니다.</li>
<li>room_number 배열은 모든 고객이 방을 배정받을 수 있는 경우만 입력으로 주어집니다.

<ul>
<li>예를 들어, k = 5, room_number = [5, 5] 와 같은 경우는 방을 배정받지 못하는 고객이 발생하므로 이런 경우는 입력으로 주어지지 않습니다.</li>
</ul></li>
</ul>

<hr>

<h5><strong>[입출력 예]</strong></h5>
<table class="table">
        <thead><tr>
<th>k</th>
<th>room_number</th>
<th>result</th>
</tr>
</thead>
        <tbody><tr>
<td>10</td>
<td>[1,3,4,1,3,1]</td>
<td>[1,3,4,2,5,6]</td>
</tr>
</tbody>
      </table>
<h5><strong>입출력 예에 대한 설명</strong></h5>

<p><strong>입출력 예 #1</strong></p>

<p>문제의 예시와 같습니다.</p>

<p>첫 번째 ~ 세 번째 고객까지는 원하는 방이 비어 있으므로 즉시 배정받을 수 있습니다. 네 번째 고객의 경우 1번 방을 배정받기를 원했는데, 1번 방은 빈 방이 아니므로, 1번 보다 번호가 크고 비어 있는 방 중에서 가장 번호가 작은 방을 배정해야 합니다. 1번 보다 번호가 크면서 비어있는 방은 [2번, 5번, 6번...] 방이며, 이중 가장 번호가 작은 방은 2번 방입니다. 따라서 네 번째 고객은 2번 방을 배정받습니다. 마찬가지로 5, 6번째 고객은 각각 5번, 6번 방을 배정받게 됩니다.</p>


> 출처: 프로그래머스 코딩 테스트 연습, https://school.programmers.co.kr/learn/challenges