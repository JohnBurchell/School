%%% @author  <John@JOHN-PC>
%%% @copyright (C) 2013, 
%%% @doc
%%% More examples from learnyousomeearlang.com
%%% @end
%%% Created : 29 Sep 2013 by  <John@JOHN-PC>

-module(kitchen).
-compile(export_all).

fridge1() ->
    receive 
	{From, {store, _Food}} ->
	    From ! {self(), ok},
	    fridge1();
	{From, {take, _Food}} ->
	    %% uh..
	    From ! {self(), not_found},
	    fridge1();
	terminate ->
	    ok
    end.

fridge2(FoodList) ->
    receive
	{From, {store, Food}} ->
	    From ! {self(), ok},
	    fridge2([Food|FoodList]);
	{From, {take, Food}} ->
	    case lists:member(Food, FoodList) of
		true ->
		    From ! {self(), {ok, Food}},
		    fridge2(lists:delete(Food, FoodList));
		false ->
		    From ! {self(), not_found},
		    fridge2(FoodList)
	    end;
	terminate ->
	    ok
    end.

store(Pid, Food) ->
    Pid ! {self(), {store, Food}},
    receive
	{Pid, Msg} ->
	    Msg
    end.

take(Pid, Food) ->
    Pid ! {self(), {take, Food}},
    receive
	{Pid, Msg} ->
	    Msg
    end.

%% Module returns the current module name
start(FoodList) ->
    spawn(?MODULE, fridge2, [FoodList]).

store2(Pid, Food) ->
    Pid ! {self(), {store, Food}},
    receive
	{Pid, Msg} -> Msg
    after 3000 ->
	    timeout
    end.

take2(Pid, Food) ->
    Pid ! {self(), {take, Food}},
    receive
	{Pid, Msg} -> Msg
    after 3000 ->
	    timeout
    end.


