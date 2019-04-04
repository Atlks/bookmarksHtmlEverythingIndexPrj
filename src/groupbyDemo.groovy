


def list = ['a', 'b', 'abc', 'ab', 'c', 'bc']
list.groupBy {it.length()
}
println  list

def errorCodeList = [
	[code: "1", language: "2", content: "3"],
	[code: "1", language: "2", content: "4"],
	[code: "1", language: "3", content: "5"],
	[code: "1", language: "3", content: "6"],
	[code: "2", language: "1", content: "3"],
	[code: "2", language: "2", content: "3"],
	[code: "1", language: "2", content: "4"]
]


def b =  errorCodeList.groupBy {
            it.code
        }.collectEntries {k, v ->
            [(k): v.groupBy {
                it.language
            }.collectEntries {k2, v2 ->
                [(k2): v2.collect {
                    it.content
                }]
            }]
        }
        println b
 