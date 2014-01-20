using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WpfApp_Prototype_v._2._0
{
    public class StockList
    {
        public class Data
        {
            public string TimeStamp { get; set; }
            public string LocalTimestamp { get; set; }
            public double Price { get; set; }
            public double Change { get; set; }
            public double ChangePercent { get; set; }
            public double High { get; set; }
            public double Low { get; set; }
            public double Open { get; set; }
            public double Volume { get; set; }
        }

        public class Value
        {
            public string Market { get; set; }
            public string Name { get; set; }
            public string Symbol { get; set; }
            public Data Data { get; set; }
        }

        public class Row
        {
            public string id { get; set; }
            public string key { get; set; }
            public Value value { get; set; }
        }

        public class RootObject
        {
            public int total_rows { get; set; }
            public int offset { get; set; }
            public List<Row> rows { get; set; }
        }
    }
}
