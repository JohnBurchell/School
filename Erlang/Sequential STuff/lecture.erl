%%% @author John <john@john-K55VD>
%%% @copyright (C) 2013, John
%%% @doc
%%% Lecture stuff with robert.
%%% @end
%%% Created : 30 Sep 2013 by John <john@john-K55VD>

-module(lecture).
-compile(export_all).

%% Roberts suggestions for 5.C.

parse(S) ->
    {Node, []} = expr(S),
    Node.

expr([$( | S0]) ->
    {Left, S1} = expr(S0),
    {Op, S2} = oper(S1),
    {Right, S3} = expr(S2),
    [$) | S4] = S3,
    {{Op, Left, Right}, S4};

expr(S) -> number(S).

oper([$* | S]) -> {mul, S};
oper([$+ | S]) -> {plus, S};
oper([$- | S]) -> {minus, S};
oper([$/ | S]) -> {divv, S}.

number([C | S]) when C >= $0, C =< $9 -> number(S, C-$0).
number([C|S], Acc) when C >= $0, C =< $9 ->
    number(S, 10*Acc+(C-$0));
number(S, Acc) -> {{num, Acc}, S}.

    
