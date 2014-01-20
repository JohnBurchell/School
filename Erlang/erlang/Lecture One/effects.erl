%Side effects, 2.3 Part 1.

-module(effects).

-export([print/1, even_print/1]).


print(N) when N > 0 ->
    print(N-1),
    io:format("Number: ~p ~n", [N]);

print(0)->
    io:format("Number: ~p ~n", [0]).

even_print(N) when N > 0 ->
    even_print(N-1),
    print_even(N, (N rem 2 == 0));

even_print(0) -> io:format("Number: ~p ~n", [0]).

print_even(N, true)->
    io:format("number: ~p ~n", [N]);
print_even(_, _)-> 0.
