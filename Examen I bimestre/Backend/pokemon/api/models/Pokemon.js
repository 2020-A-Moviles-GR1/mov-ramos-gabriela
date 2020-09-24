/**
 * Pokemon.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {


  attributes: {

    nombre:{
      type: 'string'
    },
    tipo:{
      type: 'string'
    },  nivel:{
      type: 'number'

    },
    activo:{
      type: 'boolean'

    },
    entrenador: { // Many to One (nombre FK) - mismo nombre q la relacion
      model: 'entrenador',
      required: true // (Es opcional 1 muchos 0 muchos)
    }
  },
};
