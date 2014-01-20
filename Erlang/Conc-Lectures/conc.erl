%%% @author John <john@john-K55VD>
%%% @copyright (C) 2013, John
%%% @doc
%%% Concurrency exercises.
%%% @end
%%% Created : 30 Sep 2013 by John <john@john-K55VD>

-module(conc).
-export([start/0, stop/0, print/1]).


start() ->
    Pid = spawn(conc, loop, []),
    Pid ! {self(), hello},
    receive
	{Pid, Msg} ->
	    io:format("~w~n", Msg)
    end,
    Pid ! stop.

loop() ->
    receive
	{From, Msg} ->
	    From ! {self(), Msg},
	    loop();
	stop ->
	    true
    after 1000 ->
	    {failed, timeout}
    end.

stop() ->
    ok.
print(Term) ->
    Term,
    ok.

