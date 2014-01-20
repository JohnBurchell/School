using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json;
using System.IO;

namespace WpfApp_Prototype_v._2._0
{
    public static class couchDb
    {
        static List<String> markets = new List<string> { "nasdaq", "dax", "lse" };
        static WebClient client = new WebClient();
        static String dbServer = "http://83.254.83.56:8001/";
        public static List<StockList.Value> myStocks = new List<StockList.Value>();
        public static List<StockList.Value> Nasdaq = new List<StockList.Value>();
        public static List<StockList.Value> Dax = new List<StockList.Value>();
        public static List<StockList.Value> Lse = new List<StockList.Value>();
        static List<SymbolList.Value> mySymbols = new List<SymbolList.Value>();

        public static List<SymbolList.Value> getMySymbols(String user = null)
        {
            //var client = new WebClient();
            //client.Headers.Add("User-Agent", "Nobody"); //my endpoint needs this...
            try
            {
                var response = client.DownloadString(new Uri(dbServer + "users/_design/user_info/_view/get_symbols?key=%22nico%22")); // + user + "%22));"

                var j = JsonConvert.DeserializeObject<SymbolList.RootObject>(response);

                mySymbols = j.rows[0].value;

                return mySymbols;
            }
            catch (Exception e)
            {
                Console.WriteLine("An error occoured '{0}'", e);
                System.Windows.MessageBox.Show("No symbols found in 'My Stocks' or couldn't connect to the database. Please proofe internet connectivity!");
                return null;
            }
        }

        public static void getMyStocks(List<SymbolList.Value> Keys = null)
        {
            Keys = getMySymbols();

            try
            {

                var response = "";
                //StockList.RootObject json = new StockList.RootObject();
                //client.Headers.Add("User-Agent", "Nobody"); //my endpoint needs this...

                if (Keys != null)
                {
                    myStocks.Clear();
                    for (int i = 0; i < Keys.Count; i++)
                    {
                        response = client.DownloadString(new Uri(dbServer + Keys[i].Market + "_stocks/_design/latestData/_view/latestData?key=" + "\"" + Keys[i].Symbol + "\""));
                        myStocks.Add(JsonConvert.DeserializeObject<StockList.RootObject>(response).rows[0].value);
                    }
                }
            }
            catch (Exception e)
            {
                Console.WriteLine("An error occoured '{0}'", e);
            }

                //return myStocks;
        }

        public static void getAllStocks()
        {
            try
            {
                Nasdaq.Clear();
                Dax.Clear();
                Lse.Clear();

                for (int k = 0; k < markets.Count(); k++)
                {
                    var response = "";

                    response = client.DownloadString(new Uri(dbServer + markets[k] + "_stocks/_design/latestData/_view/latestData"));
                    var j = JsonConvert.DeserializeObject<StockList.RootObject>(response);

                    for (int i = 0; i < j.rows.Count; i++)
                    {
                        switch (markets[k])
                        {
                            case "nasdaq": Nasdaq.Add(j.rows[i].value);
                                break;
                            case "dax": Dax.Add(j.rows[i].value);
                                break;
                            case "lse": Lse.Add(j.rows[i].value);
                                break;
                        }
                    }
                }
            }
            catch (Exception e)
            {
                Console.WriteLine("An error occoured '{0}'", e);
            }
        }

        public static void addStock(string Symbol, string Market)
        {
            try
            {
                var httpWebRequest = (HttpWebRequest)WebRequest.Create("http://83.254.83.56:8001/users/_design/update/_update/update_user/nico");
                httpWebRequest.ContentType = "application/json";
                httpWebRequest.Method = "PUT";

                using (var streamWriter = new StreamWriter(httpWebRequest.GetRequestStream()))
                {
                    string json = "{\"Symbol\":\"" + Symbol + "\", \"Market\":\"" + Market + "\"}";

                    streamWriter.Write(json);
                }
                var httpResponse = (HttpWebResponse)httpWebRequest.GetResponse();
                using (var streamReader = new StreamReader(httpResponse.GetResponseStream()))
                {
                    var responseText = streamReader.ReadToEnd();
                    //Now you have your response.
                    //or false depending on information in the response
                    Console.WriteLine("@@@CouchDb Server@@@: " + responseText);
                    //return true;
                }
                getMyStocks();
            }
            catch (Exception e)
            {
                Console.WriteLine("An error occoured '{0}'", e);
            }
        }

        public static void deleteStock(string Symbol)
        {
            try
            {
                var httpWebRequest = (HttpWebRequest)WebRequest.Create("http://83.254.83.56:8001/users/_design/update/_update/delete_symbol/nico");
                httpWebRequest.ContentType = "application/json";
                httpWebRequest.Method = "PUT";

                using (var streamWriter = new StreamWriter(httpWebRequest.GetRequestStream()))
                {
                    string json = "{\"Symbol\":\"" + Symbol + "\"}";

                    streamWriter.Write(json);
                }
                var httpResponse = (HttpWebResponse)httpWebRequest.GetResponse();
                using (var streamReader = new StreamReader(httpResponse.GetResponseStream()))
                {
                    var responseText = streamReader.ReadToEnd();
                    //Now you have your response.
                    //or false depending on information in the response
                    Console.WriteLine("@@@CouchDb Server@@@: " + responseText);
                    //return true;
                }
                getMyStocks();
            }
            catch (Exception e)
            {
                Console.WriteLine("An error occoured '{0}'", e);
            }
        }

        public static bool isMyStock(String Symbol) 
        {
            for (int i = 0; i < myStocks.Count(); i++)
            {
                if (myStocks[i].Symbol == Symbol) return true;
            }
            return false;
        }
    }
}
