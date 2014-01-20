-module(fixed).

-export([add/2, sum/1]).

%%Function that adds two numbers
%%HINT:this function is seriously broken!

add(A, B) ->
    A + B.

%%Function that sums a list of numbers
%%Hint: There is a small mistake here
sum([]) -> 0;
sum([H | T]) -> H + sum(T).
