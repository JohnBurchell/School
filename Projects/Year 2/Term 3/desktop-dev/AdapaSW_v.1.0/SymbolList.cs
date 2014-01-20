using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WpfApp_Prototype_v._2._0
{
    public class SymbolList
    {
        public class Value
        {
            public string Symbol { get; set; }
            public string Market { get; set; }
        }

        public class Row
        {
            public string id { get; set; }
            public string key { get; set; }
            public List<Value> value { get; set; }
        }

        public class RootObject
        {
            public int total_rows { get; set; }
            public int offset { get; set; }
            public List<Row> rows { get; set; }
        }
    }
}
