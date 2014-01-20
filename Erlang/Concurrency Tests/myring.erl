%%% @author John <john@john-K55VD>
%%% @copyright (C) 2013, John
%%% @doc
%%% ring example of benchmarking.
%%% @end
%%% Created : 25 Sep 2013 by John <john@john-K55VD>

-module(myring).
-export([start/1, start_proc/2]).

start(Num) ->
    start_proc(Num, self()).

start_proc(0, Pid) ->
    Pid ! ok;
start_proc(Num, Pid) ->
    NPid = spawn(?MODULE, start_proc, [Num-1, Pid]),
    NPid ! ok,
    receive ok -> ok end.

