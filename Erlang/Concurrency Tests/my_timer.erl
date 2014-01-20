%%% @author John <john@john-K55VD>
%%% @copyright (C) 2013, John
%%% @doc
%%% Timer introduction in the book.
%%% @end
%%% Created : 25 Sep 2013 by John <john@john-K55VD>

-module(my_timer).

-export([send_after/2, sleep/1, send/3]).

send_after(Time, Msg) ->
    spawn(my_timer, send, [self(), Time, Msg]).

send(Pid, Time, Msg) ->
    receive
    after
	Time ->
	    Pid ! Msg
    end.

sleep(T) ->
    receive
    after
	T ->
	     true
    end.


