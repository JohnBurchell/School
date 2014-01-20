%%% @author  <John@JOHN-PC>
%%% @copyright (C) 2013, 
%%% @doc
%%% Event Module.
%%% @end
%%% Created : 29 Sep 2013 by  <John@JOHN-PC>

-module(event).
-compile(export_all).


-record(state, {server,
		name="",
		to_go=0}).


loop(S = #state{server=Server}) ->
    receive
	{Server, Ref, cancel} ->
	    Server ! {Ref, ok}
    %% the *1000 is to make the timer to ms from s.
    after S#state.to_go*1000 ->
	Server ! {done, S#state.name}
    end.
