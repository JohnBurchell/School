%Database lists.

-module(db).

-export([new/0, write/3, destroy/1, read/2, delete/2, match/2]).


new()->[].
destroy(_)-> ok.

%write(Key, Val, Db)->
%    [{Key, Val} | Db];

%Writes a new key val pair to an empty Db.
write(Key, Val, [])->
    [{Key, Val}];

%Overwrites existing keys.
write(Key, Val, [{Key, _} | Db]) ->
    [{Key, Val} | Db];

%Adds new keys to the existing Db.
write(Key, Val, [Pair | Db])->
    [Pair | write(Key, Val, Db)].


read(Key, [{Key, Val} | _]) ->
    {ok, Val};
read(Key, [_ | Db]) ->
    read(Key, Db);
read(_Key, []) ->
    {error, instance}.
%Delete - Find the correct pair, recurse down the list and remove them.
%Returning the recursed structure of the original Db
%MAKE SURE to rebuild the db on the way back up.

%Base case, deletes the key if the keys match.
delete(Key, [{Key,_Val} | Db])->
    Db;

%This should search for the key if it's not matching.
%This works because the Key is not the same as the
%Key in the pair, thus it searches lower in the list
%For the correct Key.
delete(Key, [Pair | Db]) ->
    [Pair|delete(Key, Db)];

%If we get an empty DB we return the empty list.
delete(_Key, []) ->
    [].

%Fetch - This is basically a search, it should return which lists the
%searched for key belongs to.

match(Val, [{Key, Val} | Db])->
   [Key | match(Val, Db)];
match(Val, [_Pair | Db])->
    match(Val, Db);
match(_Val, []) ->
    [].

