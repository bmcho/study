using AutoMapper;
using Microsoft.AspNetCore.Mvc;
using RESTfulAPI.Helpers;
using RESTfulAPI.Models;
using RESTfulAPI.Services;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace RESTfulAPI.Controllers
{
    [ApiController]
    [Route("api/bands/")]
    public class BandsController : ControllerBase
    {
        private readonly IBandAlbumRepository _bandAlbumRepository;
        private readonly IMapper _mapper;

        public BandsController(IBandAlbumRepository bandAlbumRepository, IMapper mapper)
        {
            _bandAlbumRepository = bandAlbumRepository ?? 
                throw new ArgumentNullException(nameof(bandAlbumRepository));

            _mapper = mapper ??
                throw new ArgumentNullException(nameof(mapper ));
        }
        
        [HttpGet]
        [HttpHead]
        //public IActionResult GetBands()
        //ActionResult<T> 특정 T의 ActionResult를 반환
        public ActionResult<IEnumerable<BandDto>> GetBands([FromQuery]string mainGenre)
        {
            var bandFromRepo = _bandAlbumRepository.GetBands(mainGenre);
            
            //var bandsDto = new List<BandDto>();
            //foreach (var band in bandFromRepo)
            //{
            //    bandsDto.Add(new BandDto()
            //    {
            //        Id = band.Id,
            //        Name = band.Name,
            //        MainGenre = band.MainGenre,
            //        FoundedYearsAgo = $"{band.Founded.ToString("yyyy")}({band.Founded.GetYeasrAgo()} years ago)"
            //    });
            //}
            //return Ok(bandsDto);

            //AutoMaaper
            return Ok(_mapper.Map<IEnumerable<BandDto>>(bandFromRepo));
        }

        [HttpGet("{bandId}")]
        public IActionResult GetBand(Guid bandId)
        {
            //if (!_bandAlbumRepository.BandExisits(bandId))
            //    return NotFound();

            var bandFromRepo = _bandAlbumRepository.GetBand(bandId);

            if (bandFromRepo == null)
                return NotFound();

            return new JsonResult(bandFromRepo);
        }
    }
}
