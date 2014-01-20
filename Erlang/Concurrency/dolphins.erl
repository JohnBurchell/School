%%% @author  <John@JOHN-PC>
%%% @copyright (C) 2013, 
%%% @doc
%%% Example from learnyousomeearlang.com
%%% @end
%%% Created : 29 Sep 2013 by  <John@JOHN-PC>

-module(dolphins).
-compile(export_all).

dolphin1() ->
    receive
	do_a_flip ->
	    io:format("How about no?~n");
	fish ->
	    io:format("Thanks for all the fish!~n");
	_ ->
	    io:format("Heh, we're smarter than humans.~n")
    end.

dolphin2() ->
    receive
	{From, do_a_flip} ->
	    From ! "NO!";
	{From, fish} ->
	    From ! "Oooh i like fish!";
	_ -> 
	    io:format("heh, we're smarter than yoghurt pots~n.")
    end.

dolphin3() ->
    receive
	{From, do_a_flip} ->
	    From ! "How about NO!",
	    dolphin3();
	{From, fish} ->
	    From ! "So long, and thanks for all the fish!";
	_ ->
	    io:format("Heh, we're smarter than all the humans!~n"),
	    dolphin3()
    end.
