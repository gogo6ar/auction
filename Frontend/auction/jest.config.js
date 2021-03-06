module.exports = {
    moduleFileExtensions: [
        'js',
        'jsx',
        'json',
        'vue'
    ],
    transform: {
        // '^.+\\.vue$': 'vue-jest',
        '^.+\\.vue$': '@vue/vue3-jest',

        '.+\\.(styl|less|sass|scss|svg|png|jpg|ttf|woff|woff2)$': 'jest-transform-stub',
        // '^.+\\.jsx?$': 'babel-jest'
        '^.+\\.[t|j]sx?$': 'babel-jest',
    },
    moduleNameMapper: {
        '^@/(.*)$': '<rootDir>/src/$1'
    },
    snapshotSerializers: [
        'jest-serializer-vue'
    ],
    transformIgnorePatterns: [
        'node_modules/(?!(ag-grid-community)/)',
        'node_modules/(?!(ag-grid-vue)/)',
        'node_modules/(?!(ag-grid-vue3)/)',
    ],
    testMatch: [
        '**/tests/unit/**/*.spec.(js|jsx|ts|tsx)|**/__tests__/*.(js|jsx|ts|tsx)'
    ],
    testURL: 'http://localhost/'
}
