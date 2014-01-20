%%% @author John <john@john-K55VD>
%%% @copyright (C) 2013, John
%%% @doc
%%% Quicksort testing from the lecture on the 16th of September.
%%% @end
%%% Created : 16 Sep 2013 by John <john@john-K55VD>

-module(sorting).

-export([verbose_qs/1, qsort1/1, gen_qsort/2]).


verbose_qs([]) -> [];
verbose_qs([X]) -> [X]; 
verbose_qs([Pivot | Xs]) ->
    Smaller = less_than_or_equal(Pivot, Xs),
    Larger = greater_than(Pivot, Xs),
    verbose_qs(Smaller) ++ [Pivot] ++ verbose_qs(Larger).


greater_than(_P, []) -> [];
greater_than(P, [H | T]) when H > P -> [H | greater_than(P, T)]; 
greater_than(P, [_H | T]) -> greater_than(P, T).

less_than_or_equal(_P, []) -> [];
less_than_or_equal(P, [H | T]) when H =< P -> [H | less_than_or_equal(P, T)];
less_than_or_equal(P, [_H | T]) -> less_than_or_equal(P, T). 
				       
%% Another Q Sort
qsort1([]) -> [];
qsort1([X]) -> [X];
qsort1([Pivot | Xs]) -> 
    Smaller = [X || X <- Xs, X =< Pivot],
    Larger  = [X || X <- Xs, X >  Pivot],
    qsort1(Smaller) ++ [Pivot] ++ qsort1(Larger).
		   
% General quicksort
-spec gen_qsort(CmpFun :: fun((any(), any()) -> boolean()), [any()]) -> [any()].
gen_qsort(_CmpFun, []) -> [];
gen_qsort(_CmpFun, [X]) -> [X];
gen_qsort(CmpFun, [Pivot | Xs]) -> 
    gen_qsort(CmpFun, [ X || X <- Xs, CmpFun(X, Pivot)] 
      ++ [Pivot]
      ++ gen_qsort(CmpFun, [ X || X <- Xs, not CmpFun(X, Pivot) ])).
