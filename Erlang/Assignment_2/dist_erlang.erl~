%%% Name            :
%%% Personal number :
%%% E-mail          :

-module(dist_erlang).

%% DO NOT CHANGE THE EXPORT STATEMENT!
-export([start/0, init/0, stop/0, store/2, fetch/1, flush/0,
         task/1, dist_task/1, pmap/2, faulty_task/1
        ]).
%% End of no-change area

start() ->
  ok.

stop() ->
  ok.

store(_, _) ->
  ok.

fetch(_) ->
  ok.

flush() ->
  ok.

init() ->
  ok.

%%
%% Problem 2
%%

%% Do not change the following two functions!
task(N) when N < 0; N > 100 -> 
    exit(parameter_out_of_range);
task(N) ->
    timer:sleep(N * 2),
    256 + 17 *((N rem 13) + 3). 

faulty_task(N) when N < 0; N > 100 -> 
    exit(parameter_out_of_range);
faulty_task(N) ->
    timer:sleep(N * 2),
    {_,_,X} = now(),
    case X < 100 of
	false ->
	    256 + 17 *((N rem 13) + 3);
	true  ->
	    throw(unexpected_error)
    end.
%% End of no-change area

dist_task(_) ->
  ok.

pmap(_, _) ->
  ok.





