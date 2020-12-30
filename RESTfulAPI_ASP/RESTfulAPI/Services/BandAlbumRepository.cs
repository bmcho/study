using RESTfulAPI.DbContexts;
using RESTfulAPI.Entities;
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
            throw new NotImplementedException();
        }

        public IEnumerable<Album> GetAlbums(Guid bandId)
        {
            throw new NotImplementedException();
        }

        public Band GetBand(Guid bandId)
        {
            throw new NotImplementedException();
        }

        public IEnumerable<Band> GetBands()
        {
            throw new NotImplementedException();
        }

        public IEnumerable<Band> GetBands(IEnumerable<Guid> bandIds)
        {
            throw new NotImplementedException();
        }

        public bool Save()
        {
            throw new NotImplementedException();
        }

        public void UpdateAlbum(Album album)
        {
            throw new NotImplementedException();
        }

        public void UpdateAlbum(Band band)
        {
            throw new NotImplementedException();
        }
    }
}
