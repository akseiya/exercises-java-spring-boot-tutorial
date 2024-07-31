Feature: Fibonacci numbers

  As a curious mathematicianor
  I want to get N-th number of the Fibonacci sequence
  So that I can write my Sanscrit prosodies doubleplusgood

  Scenario Outline: First two numbers are 0 and 1
    When I ask for <initial> Fibonacci number
    Then I get the predefined number <value>
    Examples:
      | initial | value |
      | 1st     | 0     |
      | 2nd     | 1     |

  Scenario: After 0 and 1, each number is the sum of its two predecessors
    When I ask for 3rd or later Fibonacci number
    Then I always get the sum of its two predecessors
