using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace RESTfulAPI.Models
{
    public class BandForCreatingDto
    {
        public string Name { get; set; }
        public DateTime Founded { get; set; }
        public string MainGenre { get; set; }
    }
}
