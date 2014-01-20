%Section 2 problems
%Sum - Given a positive int N retrun sum of all ints between 1 and N.

-module(sectionTwo).

-export([interval/2]).


%Sum ---
sum(0)-> 0;
sum(N)->
    N + sum(N -1).

%Sum Interval
%Give 2 ints, N and M,  where N =< M return sum of intervals
%between N and M. if N > M terminate.

interval(N, M) when N < M->
     sum(M);

interval(N, M) when N == M ->
    N.
    

