import axios from 'axios';
import authHeader from "@/services/auth-header";
import properties from "@/properties";

const API_URL = properties.API_URL + '/api/auction/';

class AuctionsService {
    getAuctions(perPage) {
        return axios.get(API_URL, {params: {perPage: perPage}});
    }

    getAuctionById(auctionId) {
        return axios.get(API_URL + auctionId);
    }

    getByOwner(userId, page, perPage) {
        return axios.get(API_URL + 'owner/' + userId, {
            params: {
                page: page,
                perPage: perPage
            },
            headers:
                authHeader()
        })
    }

    getByParticipant(userId, page, perPage) {
        return axios.get(API_URL + 'participant/' + userId, {
            params: {
                page: page,
                perPage: perPage
            },
            headers:
                authHeader()
        })
    }

    create(auction) {
        return axios.post(API_URL, {
            title: auction.title,
            description: auction.description,
            startPrice: auction.startPrice,
            finishPrice: auction.finishPrice,
            categoryIds: auction.categoryIds,
            images: auction.images,
            userId: auction.userId,
            startDate: auction.startDate,
            finishDate: auction.finishDate,
            auctionType: auction.auctionType,
            charityPercent : auction.charityPercent
        }, {
            headers:
                authHeader()
        });
    }

    update(auction) {
        return axios.put(API_URL + auction.id, {
            auction,
        }, {
            headers:
                authHeader()
        });
    }

    delete(auctionId) {
        return axios.delete(API_URL + auctionId, {
            headers:
                authHeader()
        });
    }

    getAll(page, perPage) {
        return axios.get(API_URL, {
            params: {
                page: page,
                perPage: perPage
            }
        });
    }

    findAll() {
        return axios.get(API_URL + 'all');
    }

    filter(page, perPage, categories, title, statuses) {
        return axios.get(API_URL + 'filter', {
            params: {
                page: page,
                perPage: perPage,
                title: title,
                categoryIds: categories,
                statuses: statuses,
            }
        })
    }
}

export default new AuctionsService();
