


def list = ['a', 'b', 'abc', 'ab', 'c', 'bc']
list.groupBy {
	it.length()
}
println  list

def tab = [
	[code: "code1", language: 2, content: "aaa"],
	[code: "code1", language: 4, content: "bbbb"],
	 
	[code: "code2", language: 3, content: "ccc"],
	 
]

//  first groupBy, which returns Map<grpbyKey, List>
def b =  tab.groupBy {
	it.code
}.collect  {
	
	//Entries
	// ...and transform List into Map
	  code, List value ->
	    def line=[code: value.sum { e->e.language }]
        return 	line
		//return a map
	
}

//
//  b =  tab.collect {
//	      tab.groupBy(  (it.code,tab.sum {e->e.language })->{
//			  return a;
//		  }        )
//	
//   } 
//	
  b =  tab.groupBy {
	it.code
}

//[code1:6, code2:3]
println b
