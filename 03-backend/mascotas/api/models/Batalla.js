/**
 * Batalla.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {

   
    Nombre:{
      type: 'string'
    },
   
    batalla: { 
      collection: 'pokemon',
      via : "batalla"
    //  required: true // (Es opcional 1 muchos 0 muchos)
    }
    
  },

};

