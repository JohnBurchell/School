%Lists - Advanced list manipulation.

-module(alists).

-export([filter/2]).

filter([], _)-> [];
filter([H | T], X) when H < X->
   [H | filter(T, X)];
filter([H | _], X) when H > X -> [].
