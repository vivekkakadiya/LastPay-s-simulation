'use client'

import { useState } from 'react'
import { useRouter } from 'next/navigation'

interface Car {
  model: string
  brand: string
  price: number
  category: string
  fuelType: string
  year: number
}

export default function HomePage() {
  const [customerName, setCustomerName] = useState('')
  const [selectedCarModel, setSelectedCarModel] = useState('')
  const [isLoading, setIsLoading] = useState(false)
  const router = useRouter()

  const carOptions = [
    { value: 'TOYOTA_CAMRY', label: 'Toyota Camry - $25,000' },
    { value: 'BMW_X3', label: 'BMW X3 - $45,000' },
    { value: 'TESLA_MODEL_3', label: 'Tesla Model 3 - $35,000' },
  ]

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault()
    
    if (!customerName || !selectedCarModel) {
      alert('Please fill in all fields')
      return
    }

    setIsLoading(true)

    try {
      const response = await fetch('http://localhost:8080/api/request', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          customerName,
          selectedCarModel,
        }),
      })

      if (!response.ok) {
        throw new Error('Failed to process request')
      }

      const result = await response.json()
      
      // Store result in localStorage and navigate to summary page
      localStorage.setItem('lastPayResult', JSON.stringify(result))
      router.push('/summary')
      
    } catch (error) {
      console.error('Error:', error)
      alert('Failed to process your request. Please try again.')
    } finally {
      setIsLoading(false)
    }
  }

  return (
    <div className="max-w-2xl mx-auto">
      <div className="bg-white rounded-lg shadow-md p-8">
        <h2 className="text-3xl font-bold text-gray-900 mb-2">Car Financing Application</h2>
        <p className="text-gray-600 mb-8">Get instant approval for your dream car with integrated financing</p>
        
        <form onSubmit={handleSubmit} className="space-y-6">
          <div>
            <label htmlFor="customerName" className="block text-sm font-medium text-gray-700 mb-2">
              Full Name
            </label>
            <input
              type="text"
              id="customerName"
              value={customerName}
              onChange={(e) => setCustomerName(e.target.value)}
              className="w-full px-4 py-3 border border-gray-300 rounded-md focus:ring-2 focus:ring-blue-500 focus:border-transparent"
              placeholder="Enter your full name"
              required
            />
          </div>

          <div>
            <label htmlFor="carModel" className="block text-sm font-medium text-gray-700 mb-2">
              Select Car Model
            </label>
            <select
              id="carModel"
              value={selectedCarModel}
              onChange={(e) => setSelectedCarModel(e.target.value)}
              className="w-full px-4 py-3 border border-gray-300 rounded-md focus:ring-2 focus:ring-blue-500 focus:border-transparent"
              required
            >
              <option value="">Choose a car model</option>
              {carOptions.map((car) => (
                <option key={car.value} value={car.value}>
                  {car.label}
                </option>
              ))}
            </select>
          </div>

          <button
            type="submit"
            disabled={isLoading}
            className="w-full bg-blue-600 text-white py-3 px-6 rounded-md hover:bg-blue-700 focus:ring-2 focus:ring-blue-500 focus:ring-offset-2 disabled:opacity-50 disabled:cursor-not-allowed transition-colors"
          >
            {isLoading ? 'Processing...' : 'Submit Application'}
          </button>
        </form>

        <div className="mt-8 p-4 bg-blue-50 rounded-md">
          <h3 className="text-lg font-semibold text-blue-900 mb-2">What happens next?</h3>
          <ul className="text-blue-800 space-y-1">
            <li>• We'll check car availability and pricing</li>
            <li>• Get you an instant insurance quote</li>
            <li>• Process your loan application</li>
            <li>• Provide you with a complete financing package</li>
          </ul>
        </div>
      </div>
    </div>
  )
}
