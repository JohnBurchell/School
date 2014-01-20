%%% @author  <John@JOHN-PC>
%%% @copyright (C) 2013, 
%%% @doc
%%% Multiproc - some cool stuff! Prioritises things, and does them first, kinda fun.
%%% @end
%%% Created : 29 Sep 2013 by  <John@JOHN-PC>

-module(multiproc).
-compile(export_all).

sleep(T)->
    receive
	after T -> ok
	end.

flush() ->
    receive
	_ -> flush()
    after 0->
	    ok
    end.

important() ->
    receive
	{Priority, Message} when Priority > 10 ->
	    [Message | important()]
    after 0 ->
	    normal()
    end.

normal() ->
    receive
	{_, Message} ->
	    [Message | normal()]
    after 0 ->
	    []
    end.		
    
