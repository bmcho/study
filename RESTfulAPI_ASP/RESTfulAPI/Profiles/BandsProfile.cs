using AutoMapper;
using RESTfulAPI.Helpers;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace RESTfulAPI.Profiles
{
    public class BandsProfile : Profile
    {
        public BandsProfile()
        {
            CreateMap<Entities.Band, Models.BandDto>()
                .ForMember(
                    dest => dest.FoundedYearsAgo,
                    opt => opt.MapFrom(src => $"{src.Founded.ToString("yyyy")}({src.Founded.GetYeasrAgo()}) years ago")
                );

            CreateMap<Models.BandForCreatingDto, Entities.Band>();
        }
    }
}
