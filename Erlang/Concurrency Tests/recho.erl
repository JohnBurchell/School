%%% @author John <john@john-K55VD>
%%% @copyright (C) 2013, John
%%% @doc
%%% This is another small example of using messags to pass around
%%% between functions, seems quite interesting!
%%% @end
%%% Created : 25 Sep 2013 by John <john@john-K55VD>

-module(recho).
-export([go/0, loop/0]).

go() ->
    register(echo, spawn(echo, loop, [])),
    echo ! {self(), hello},
    receive
	{_Pid, Msg} ->
	    io:format("~w~n", [Msg])
    end.

loop() ->
    receive 
	{From, Msg} ->
	    From ! {self(), Msg},
	    loop();
	stop ->
	    true
    end.

