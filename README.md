To run tests you need to set the user token:
class TriangleConstants constant PERSONAL_TOKEN

Possible issues were found:

---------------------------------------

1. When deleting unknown resource status code 200 is returned (404 is expected)

2. When creation a triangle without 'input' attribute status code 500 is returned (422 is expected as the incorrect data is sent)

---------------------------------------
 
