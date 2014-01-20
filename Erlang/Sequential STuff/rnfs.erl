%%% @author John <john@john-K55VD>
%%% @copyright (C) 2013, John
%%% @doc
%%% Records and funs from robert.
%%% @end
%%% Created : 30 Sep 2013 by John <john@john-K55VD>

-module(rnfs).
-export([new/0, destroy/1, write/3, read/2, delete/2, match/2,
	 new2/0, destroy2/1, write2/3]).

-include("dbrecord.hrl").

%% DB needs to be able to:
%% Write, Delete, Destroy, New, Read, Match

new() -> [].

destroy(_) -> ok.

%% Write new values
write(Key, Value, []) ->
    [#data{key=Key, value=Value}];

%% Overwrite existing values.
write(Key, Value, [R | Db]) when is_record(R, data),
			  R#data.key =:= Key ->
	     [#data{key=Key, value=Value} | Db];

%% Writing new values to an exisiting DB.
write(Key, Value, [R | Db]) ->
    [R | write(Key, Value, Db)].
    
%% Read values from the database.

%% Finding a key that exists.
read(Key, [R | _]) when is_record(R, data),
			R#data.key =:= Key ->
    {ok, R#data.value};

%% Key found but doesn't match, keep searching.
read(Key, [_ | T]) -> read(Key, T);

%% No key found, end of the list is reached, return error.
%% At this point, you don't care what the key is.
read(_Key, []) ->
    {error, notfound}.

%% Deletes the key if it's found.
delete(Key, [R | Db]) when is_record(R, data),
			   R#data.key =:= Key ->
    Db;

%% Continue search for key if it's not found initially.
delete(Key, [R | Db]) -> [R | delete(Key, Db)];
%% Key is not found, return an empty db..
delete(_Key, []) -> []. 

%% Match, returns the key the value belongs to.
match(Value, [R | Db]) when is_record(R, data),
			    R#data.value =:= Value ->
    [R#data.key | match(R#data.value, Db)];
%% Continue search for matches, adding to list if found.
match(Value, [_R | Db]) ->
    match(Value, Db);
%% Return empty if end of list found.
match(_Value, []) ->
    [].


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% Using left / right in the record declaration to build a tree.

new2() ->
    #tree{}.

destroy2(_) -> ok.

write2(Key, Value, R) when R#tree.key =:= empty ->
    #tree{key=Key, value=Value};

%% Write to a record with values, when the value is smaller than the
% existing one. Smaller on the left, bigger in the right.
write2(Key, Value, R) when is_record(R, tree),
			   R#tree.value =< Value ->
    R#tree{left=#tree{key=Key, value=Value}};
write2(Key, Value, R) when is_record(R, tree),
			   R#tree.value >= Value ->
    R#tree{right=#tree{key=Key, value=Value}}.


