%%% @author John <john@john-K55VD>
%%% @copyright (C) 2013, John
%%% @doc
%%% Playing with geometry
%%% @end
%%% Created : 16 Sep 2013 by John <john@john-K55VD>

-module(geo).

-export([area/1, circ/1, larger/2,
	 sum_areas/1, largest/1]).

%% This definition is almost like a typedef in C++
-define(PI, math:pi()).

-type circle()    :: {circle, number()}.
-type square()    :: {square, number()}.
-type rectangle() :: {rectangle, number(), number()}.

-type shape()     :: circle() | square() | rectangle().

%%--------------------------------------------------------------------
%% @doc
%% area/1 - Compute the area of a shape.
%% @end
%%--------------------------------------------------------------------
-spec area(Shape :: shape()) -> number().
area({circle, R}) -> R * R * ?PI;
area({square, X}) -> X * X;
area({rectangle, W, H}) -> W * H.


%%--------------------------------------------------------------------
%% @doc
%% circ/1 - Computes the circumference of a shape
%% @end
%%--------------------------------------------------------------------
-spec circ(Shape :: shape()) -> number().
circ({circle, R}) -> 2 * R * ?PI;
circ({square, X}) -> 4 * X;
circ({rectangle, W, H}) ->  2 * W * H.


%%--------------------------------------------------------------------
%% @doc
%% larger/2 - Find which of the two shapes is bigger
%% @end
%%--------------------------------------------------------------------
-spec larger(Shape1 :: shape(), Shape2 :: shape()) -> shape().
larger(Shape1, Shape2) -> 
    case area(Shape1) > area(Shape2) of
	true  -> Shape1;
	false -> Shape2 %%Note: If the sizes are equal, we return the
		        %% second shape!!
    end.


%%--------------------------------------------------------------------
%% @doc
%% sum_areas/1 - Sums the area of a list of squares
%% @end
%%--------------------------------------------------------------------
-spec sum_areas(Shapes :: [shape()]) -> number().
sum_areas([]) -> 0;
sum_areas([Shape | Shapes]) -> 
    area(Shape) + sum_areas(Shapes).

%%--------------------------------------------------------------------
%% @doc
%% largest/1 - Returns the largest of the shapes from a list.
%% @end
%%--------------------------------------------------------------------
%% The ... is saying, it should at least have one shape in the list.
-spec largest(Shapes :: [shape(), ...]) -> shape().
largest([Shape | Shapes]) -> 
    largest(Shape, Shapes).

-spec largest(LargestSoFar :: shape(), Shapes :: [shape()]) -> shape().
largest(LargestSoFar, []) -> 
    LargestSoFar;
largest(LargestSoFar, [Shape | Shapes]) -> 
    largest(larger(LargestSoFar, Shape), Shapes).

	    
 
