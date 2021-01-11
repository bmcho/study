using RESTfulAPI.DbContexts;
using RESTfulAPI.Entities;
using RESTfulAPI.Helpers;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace RESTfulAPI.Services
{
    public class BandAlbumRepository : IBandAlbumRepository
    {
        private readonly BandAlbumContext _context;

        public BandAlbumRepository(BandAlbumContext context)
        {
            _context = context ?? throw new ArgumentNullException(nameof(context)); 
        }

        public void AddAlbum(Guid bandId, Album album)
        {
            if (bandId == Guid.Empty)
                throw new ArgumentNullException(nameof(bandId));

            if (album == null)
                throw new ArgumentNullException(nameof(album));

            album.BandId = bandId;
            _context.Albums.Add(album);
        }

        public void AddAlbum(Band band)
        {
            if (band == null)
                throw new ArgumentNullException(nameof(band));

            _context.Bands.Add(band);
        }

        public bool AlbumExisits(Guid albumId)
        {
            if (albumId == Guid.Empty)
                throw new ArgumentNullException(nameof(albumId));

            return _context.Albums.Any(a => a.Id == albumId);
        }

        public bool BandExisits(Guid bandId)
        {
            if (bandId == Guid.Empty)
                throw new ArgumentNullException(nameof(bandId));

            return _context.Bands.Any(a => a.Id == bandId);
        }

        public void DeleteAlbum(Album album)
        {
            if(album == null)
                throw new ArgumentNullException(nameof(album));

            _context.Albums.Remove(album);
        }

        public void DeleteAlbum(Band band)
        {
            if (band== null)
                throw new ArgumentNullException(nameof(band));

            _context.Bands.Remove(band);
        }

        public Album GetAlbum(Guid bandId, Guid albumId)
        {
            if (bandId == Guid.Empty)
                throw new ArgumentNullException(nameof(bandId));

            if (albumId == Guid.Empty)
                throw new ArgumentNullException(nameof(albumId));

            return _context.Albums.Where(w => w.BandId == bandId && w.Id == albumId).FirstOrDefault();
        }

        public IEnumerable<Album> GetAlbums(Guid bandId)
        {
            if (bandId == Guid.Empty)
                throw new ArgumentNullException(nameof(bandId));

            var data = _context.Albums.Where(w => w.BandId == bandId);

            if (data.Count() == 0)
                throw new ArgumentNullException(nameof(bandId));

            return _context.Albums.Where(w => w.BandId == bandId);
        }

        public Band GetBand(Guid bandId)
        {
            if (bandId == Guid.Empty)
                throw new ArgumentNullException(nameof(bandId));

            return _context.Bands.FirstOrDefault(w => w.Id == bandId);
        }

        public IEnumerable<Band> GetBands()
        {
            return _context.Bands.ToList();
        }

        public IEnumerable<Band> GetBands(IEnumerable<Guid> bandIds)
        {
            if (bandIds == null)
                throw new ArgumentNullException(nameof(bandIds));

            return _context.Bands.Where(w => bandIds.Contains(w.Id))
                .OrderBy(o => o.Name).ToList();
        }

        public IEnumerable<Band> GetBands(string mainGener)
        {
            if (string.IsNullOrWhiteSpace(mainGener))
                return GetBands();

            mainGener = mainGener.Trim();
            return _context.Bands.Where(w => w.MainGenre == mainGener).ToList();
        }

        public IEnumerable<Band> GetBands(string mainGener, string searchQuery)
        {
            if (string.IsNullOrWhiteSpace(mainGener) && string.IsNullOrWhiteSpace(searchQuery))
                return GetBands();

            var collection = _context.Bands as IQueryable<Band>;

            if (!string.IsNullOrWhiteSpace(mainGener))
            {
                mainGener = mainGener.Trim();
                collection = collection.Where(w => w.MainGenre == mainGener);
            }

            if (!string.IsNullOrWhiteSpace(searchQuery))
            {
                searchQuery = searchQuery.Trim();
                collection = collection.Where(w => w.Name.Contains(searchQuery));
            }

            return collection.ToList();
        }

        public IEnumerable<Band> GetBands(BandsResourceParameters bandsResourceParameters)
        {
            if (bandsResourceParameters == null)
                throw new ArgumentException(nameof(bandsResourceParameters));

            if (string.IsNullOrWhiteSpace(bandsResourceParameters.MainGenre) && string.IsNullOrWhiteSpace(bandsResourceParameters.SearchQuery))
                return GetBands();

            var collection = _context.Bands as IQueryable<Band>;

            if (!string.IsNullOrWhiteSpace(bandsResourceParameters.MainGenre))
            {
                var mainGenre = bandsResourceParameters.MainGenre.Trim();
                collection = collection.Where(w => w.MainGenre == mainGenre);
            }

            if (!string.IsNullOrWhiteSpace(bandsResourceParameters.SearchQuery))
            {
                var searchQuery = bandsResourceParameters.SearchQuery.Trim();
                collection = collection.Where(w => w.Name.Contains(searchQuery));
            }

            return collection.ToList();
        }

        public bool Save()
        {
            return _context.SaveChanges() > 0;
        }

        public void UpdateAlbum(Album album)
        {
            
        }

        public void UpdateAlbum(Band band)
        {
            
        }
    }
}
