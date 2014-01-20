%%% @author  <John@JOHN-PC>
%%% @copyright (C) 2013, 
%%% @doc
%%%
%%% @end
%%% Created :  7 Oct 2013 by  <John@JOHN-PC>

-module(new).
-export([init/0, get_test/0, set_options/0, put_test/0]).

init() ->
    inets:start().

set_options() ->
    httpc:set_options([{version, "HTTP/1.0"}]).

get_test() ->
    %% Make it HTTP/1.0
    {ok, {{Version, 200, ReasonPhrase}, Headers, Body}} 
	= httpc:request(get, {"http://127.0.0.1:5984/john/test_doc", []}, 
			[{version, "HTTP/1.0"}], []),
    Headers.

put_test() ->
    httpc:request(put, {"http://127.0.0.1:5984/john/a_new_doc", [], 
			"application/json", "{\"Name\":\"Tom\",\"Age\":10}"},
		  [{version, "HTTP/1.0"}], []).


%% we should convert to binary, much faster / smaller than passing strings into the DB.

%% Pattern match senders for the doc_id, i.e. APPL_NASDAQ etc, would be easy to update etc. 
%% can probably be done by checking or requiring the PID / name of the sending getter.
%% This would hope that the sending process's registered name is something that we can sensibly use.

%% How da fuck do i represent JSON in erlang strings : /
