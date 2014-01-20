using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Data;
using System.Windows.Media;

namespace WpfApp_Prototype_v._2._0
{
    public class ColorConverter : IValueConverter
        {
            public object Convert(object value, Type targetType, object parameter,
                                  CultureInfo culture)
            {
                double number = (double)value;
                if (number >= 0)
                {
                    return "#91eb67";
                    //return System.Drawing.ColorTranslator.FromHtml("#91eb67");
                }

                    return "#a72d2c";
                    //return System.Drawing.ColorTranslator.FromHtml("#a72d2c");
            }

            public object ConvertBack(object value, Type targetType, object parameter,
                                      CultureInfo culture)
            {
                return "#91eb67";
                //return System.Drawing.ColorTranslator.FromHtml("#91eb67");
            }
        }

    public class Dbl0Dec : IValueConverter
    {
        public object Convert(object value, Type targetType, object parameter,
                              CultureInfo culture)
        {
            double number = (double)value;
            //convert to currency, no decimals
            return number.ToString("N0");
        }

        public object ConvertBack(object value, Type targetType, object parameter,
                                  CultureInfo culture)
        {
            return 0;
        }
    }

    public class Dbl2Dec : IValueConverter
    {
        public object Convert(object value, Type targetType, object parameter,
                              CultureInfo culture)
        {
            double number = (double)value;
            //convert to currency, two decimals
            return number.ToString("N");
        }

        public object ConvertBack(object value, Type targetType, object parameter,
                                  CultureInfo culture)
        {
            return 0;
        }
    }

    public class DblPerc : IValueConverter
    {
        public object Convert(object value, Type targetType, object parameter,
                              CultureInfo culture)
        {
            double number = (double)value/100;
            if (number >= 0)
            {
                return "(+" + number.ToString("P") + ")";
            }
                return "(" + number.ToString("P") + ")";
        }

        public object ConvertBack(object value, Type targetType, object parameter,
                                  CultureInfo culture)
        {
            return 0;
        }
    }
}
