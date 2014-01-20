%%% @author John <john@john-K55VD>
%%% @copyright (C) 2013, John
%%% @doc
%%%
%%% @end
%%% Created : 25 Sep 2013 by John <john@john-K55VD>

-module(echo).
-export([go/0, loop/0]).

%%--------------------------------------------------------------------
%% @doc
%% This is a simple example from the book which shows how messages are
%% Sent and recieved between eachother.
%% @end
%%--------------------------------------------------------------------

go() ->
    Pid = spawn(echo, loop, []),
    Pid ! {self(), hello},
    receive
	{Pid, Msg} ->
	    io:format("~w~n", [Msg])
     end,
    
    Pid ! stop.

loop() ->
    receive
	{From, Msg} ->
	       From ! {self(), Msg},
	       loop();
	stop -> 
	       true
       end.


