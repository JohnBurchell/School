%%% @author John <john@john-K55VD>
%%% @copyright (C) 2013, John
%%% @doc
%%% dbRecord.
%%% @end
%%% Created : 30 Sep 2013 by John <john@john-K55VD>


-record(data, {key, value}).
-record(tree, {key=empty, value=empty, left=empty, right=empty}).
