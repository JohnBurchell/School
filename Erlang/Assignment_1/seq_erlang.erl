%%% File    : seq_erlang.erl
%%% Author  : Hans Svensson <hanssv@hanssv-ituniv>
%%% Description : Solutions to first part of exercises
%%% Created : 14 Sep 2009 by Hans Svensson <hanssv@hanssv-ituniv>

-module(seq_erlang).
-author('John@JOHN-PC').

%-include_lib("eqc/include/eqc.hrl").
-compile(export_all).
-export([sum/1, mul/1, take_snd/1, fib/1,
         eff_fib/1,
         digitize/1, is_happy/1, all_happy/2,
         expr_parse/1, expr_print/1, expr_eval/1
         %%, expr_parse_2/1, expr_print_2/1, expr_eval_2/1
        ]).

%-type equation() :: {atom(), tuple(), tuple()}.
 
%% sum
-spec sum(integer()) -> integer().
sum(0)      -> 0;
sum(N)      -> sum(N, 0).
-spec sum(integer(), integer()) -> integer().
sum(0, Acc) -> Acc; 
sum(N, Acc) -> 
    sum(N-1, N+Acc).

-spec mul(integer()) -> integer().
%% mul
mul(1)      -> 1;
mul(N)      -> mul(N, 1).
-spec mul(integer(), integer()) -> integer().
mul(0, Acc) -> Acc; 
mul(N, Acc) -> mul(N-1, N*Acc).

-spec take_snd(list()) -> list().
%% take_snd
take_snd([])      -> [];
take_snd(List)    -> [X || {_, X} <- List].
-spec fib(integer()) -> integer().
%% Fibonacci
fib(0)            -> 1;
fib(1)            -> 1;
%% Normal fib "Algorithm", i think this returns correctly!
fib(N) when N > 1 -> fib(N - 1) + fib(N - 2).
-spec eff_fib(integer()) -> integer().
%% Efficient fib.
eff_fib(0)            -> 1;
eff_fib(1)            -> 1;
eff_fib(N) when N > 1 -> fibby(N, 1, 1).
-spec fibby(integer(), integer(), integer()) -> integer().
fibby(2, Prev, Next)  -> Prev + Next;
fibby(N, Prev, Next)  -> fibby(N-1, Next, Prev + Next).

%% Digitify a number
%% Uses a recusrsive method to build the list,
%% My initial attempt gave me the list in the wrong direction.
-spec digitize(integer()) -> list().
digitize(0)            -> [];
digitize(N) when N > 0 -> digitize_Acc(N, []).
%% Influenced from the accumilator example in the book and slides.
-spec digitize_Acc(integer(), list()) -> list().
digitize_Acc(0, Acc)-> Acc;
digitize_Acc(N, Acc)->
    digitize_Acc(N div 10, [N rem 10 | Acc]).
-spec is_happy(integer()) -> boolean().
%% is_happy
is_happy(0) -> false;
is_happy(1) -> true;
%% Should only ever need one check, if 4 appears in the list at any point
%% Number is therefor not a happy number.
is_happy(4) -> false;
is_happy(N) ->
  is_happy(happy_sum([(X * X) || X <- digitize(N)])).
%% This sums up the elements of a list.
-spec happy_sum(list()) -> integer().
happy_sum([])      -> 0;
happy_sum([H | T]) ->
    H + happy_sum(T).

%% all_happy
%% Build a standard accumilator
-spec all_happy(integer(), integer()) -> list().
all_happy(N, M) ->
    all_happy_acc(N -1, M, []).

all_happy_acc(N, N, Acc) -> Acc;
all_happy_acc(N, M, Acc) ->

    case is_happy(M) of
	true -> all_happy_acc(N, M-1, [M | Acc]);
	false -> all_happy_acc(N, M-1, Acc)
    end.

%% Expressions

%% A. Evaluate expressions
<<<<<<< HEAD
expr_eval(Expr) -> eval_val(Expr).
%% Expression pattern matches.
eval_val({num, N}) -> N;
eval_val({mul, N, M})-> eval_val(N) * eval_val(M);
eval_val({plus, N, M})-> eval_val(N) + eval_val(M);
eval_val({minus, N, M})-> eval_val(N) - eval_val(M).


%% B. Pretty print expressions
=======
-spec expr_eval(tuple()) -> integer().
expr_eval(Expr) -> eval_val(Expr).
%% Expression pattern matches.
eval_val({num, N})      -> N;
eval_val({mul, N, M})   -> eval_val(N) * eval_val(M);
eval_val({plus, N, M})  -> eval_val(N) + eval_val(M);
eval_val({minus, N, M}) -> eval_val(N) - eval_val(M).


%% B. Pretty print expressions
-spec expr_print(tuple()) -> string().
>>>>>>> 1ade3768494f417546286b267f68ff18b90e36b3
expr_print(Expr) -> make_list(Expr).
    
%% Convert the tuples to lists, and then recurse through the lists
-spec make_list(tuple()) -> list().
make_list({num, N}) -> integer_to_list(N);
make_list({Ind, V1, V2}) -> lists:concat(
			      ['(',
			       make_list(V1),
			       get_val(Ind),
			       make_list(V2),
			       ')']).

%% Might be needed for converting the list members to
%% The actual values that i want.
-spec get_val(atom()) -> string().
get_val(mul)-> "*";
get_val(minus)-> "-";
get_val(plus)-> "+";
get_val(N) -> N. 

%% C. Parse expressions
-spec expr_parse(string()) -> tuple().
expr_parse(Str)                                 ->    parse(Str).
<<<<<<< HEAD

-spec parse(list()) -> tuple().
parse([$( | T])                                 ->    parse(T, []);
parse(N)                                        ->    {num, list_to_integer(N)}.
parse([$) | []], Acc)                           ->    parse([], Acc);
parse([H | T], Acc)                             ->    parse(T, [H | Acc]);
parse([], Acc)                                  ->    split(lists:reverse(Acc), []).

-spec split(list(), list()) -> tuple().
split([H | Right], Left) when H > 41, H < 46    ->    {indi(H), parse(Left), parse(Right)};
split([H | T], Left) when H > 47, H < 58        ->    split(T, lists:reverse([H | Left]));
split(T, Left)                                  ->    par_finder(T, Left).

-spec par_finder(list(), list()) -> list().
par_finder([$( | T], Rem)                       ->    par_finder(T, 0, Rem).
par_finder([H | T], Count, Rem) when Count >= 0 ->

    case H of
	$( -> par_finder(T, (Count + 1), [$(] ++ Rem);
	$) -> par_finder(T, (Count - 1), [$)] ++ Rem);
	H  -> par_finder(T, Count, [H | Rem])

    end;

par_finder(T, N, Rem) when N =< 0               ->    Rrem = lists:reverse(Rem),
						      split(T, [$(] ++ Rrem);
par_finder([], _, Rem)                          ->    split([], Rem).


=======

-spec parse(list()) -> tuple().
parse([$( | T])                                 ->    parse(T, []);
parse(N)                                        ->    {num, list_to_integer(N)}.
parse([$) | []], Acc)                           ->    parse([], Acc);
parse([H | T], Acc)                             ->    parse(T, [H | Acc]);
parse([], Acc)                                  ->    split(lists:reverse(Acc), []).

-spec split(list(), list()) -> tuple().
split([H | Right], Left) when H > 41, H < 46    ->    {indi(H), parse(Left), parse(Right)};
split([H | T], Left) when H > 47, H < 58        ->    split(T, lists:reverse([H | Left]));
split(T, Left)                                  ->    par_finder(T, Left).

-spec par_finder(list(), list()) -> list().
par_finder([$( | T], Rem)                       ->    par_finder(T, 0, Rem).
par_finder([H | T], Count, Rem) when Count >= 0 ->

    case H of
	$( -> par_finder(T, (Count + 1), [$(] ++ Rem);
	$) -> par_finder(T, (Count - 1), [$)] ++ Rem);
	H  -> par_finder(T, Count, [H | Rem])

    end;

par_finder(T, N, Rem) when N =< 0               ->    Rrem = lists:reverse(Rem),
						      split(T, [$(] ++ Rrem);
par_finder([], _, Rem)                          ->    split([], Rem).


>>>>>>> 1ade3768494f417546286b267f68ff18b90e36b3
indi($+)                                        ->    plus;
indi($-)                                        ->    minus;
indi($*)                                        ->    mul.

%% Advanced versions of expr
%% expr_eval_2(_Expr) -> ok.

%% expr_print_2(_Expr) -> ok.

%% expr_parse_2(_Str) -> ok.

<<<<<<< HEAD
=======




>>>>>>> 1ade3768494f417546286b267f68ff18b90e36b3
