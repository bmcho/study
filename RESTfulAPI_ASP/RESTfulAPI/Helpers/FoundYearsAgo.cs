using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace RESTfulAPI.Helpers
{
    public static class FoundYearsAgo
    {
        public static int GetYeasrAgo(this DateTime dateTime)
        {
            var currentDate = DateTime.Now;
            int yearsAgo = currentDate.Year - dateTime.Year;
            
            return yearsAgo;
        }
    }
}
