%%% @author  <John@JOHN-PC>
%%% @copyright (C) 2013, 
%%% @doc
%%% Creating a process ring.
%%% @end
%%% Created :  2 Oct 2013 by  <John@JOHN-PC>

-module(ring).
%% public
-export([start/3]).

%% internal
-export([spawnLoop/3, listen/2]).

start(MessageNumber, NodeNumber, Message) ->
    register(ring, spawn(ring, spawnLoop, [MessageNumber, NodeNumber, Message])).

stop() ->
    ring ! stop.

%% Listen should hold the new Pids in a listening / waiting state
listen(N, Pid) ->
    
    receive
	update ->
	    ring:listen();
	Message ->
	    io:format("Process ~p arrived.~n~p", [N, Message]),
	    Pid ! message,
	    listen(N, Pid)
    end.

%% Starts the process spawning
spawnLoop(MessageNumber, NodeNumber, Message) ->
    Pid = spawnRinglet(NodeNumber, self()),
    Pid ! Message.

%% Continues spawning processes, using the old Pid to be an attribute
%% of the new pid, should let me leap-frog back down the processes later.
spawnRinglet(0, Pid) ->
    Pid;
spawnRinglet(N, Pid) ->
    NewPid = spawn(ring, listen, [N, Pid]),
    spawnRinglet(N-1, NewPid).

messageLooper(MessageNumber, Pid) ->
    receive
	Message ->
	    ok
end.
