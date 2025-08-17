'use client'

import { useEffect, useState } from 'react'
import { useRouter } from 'next/navigation'

interface Car {
  model: string
  brand: string
  price: number
  category: string
  fuelType: string
  year: number
}

interface InsuranceQuote {
  premium: number
  coverage: string
  provider: string
  termMonths: number
}

interface LoanApproval {
  approved: boolean
  approvedAmount: number
  interestRate: number
  termMonths: number
  bankName: string
  reason: string
}

interface CombinedResponse {
  customerName: string
  carDetails: Car
  insuranceQuote: InsuranceQuote
  loanApproval: LoanApproval
  overallApproved: boolean
}

export default function SummaryPage() {
  const [result, setResult] = useState<CombinedResponse | null>(null)
  const router = useRouter()

  useEffect(() => {
    const storedResult = localStorage.getItem('lastPayResult')
    if (storedResult) {
      setResult(JSON.parse(storedResult))
    } else {
      router.push('/')
    }
  }, [router])

  const handleNewApplication = () => {
    localStorage.removeItem('lastPayResult')
    router.push('/')
  }

  if (!result) {
    return (
      <div className="flex justify-center items-center min-h-64">
        <div className="text-lg">Loading...</div>
      </div>
    )
  }

  return (
    <div className="max-w-4xl mx-auto">
      <div className="mb-6">
        <h2 className="text-3xl font-bold text-gray-900">Application Summary</h2>
        <p className="text-gray-600">Here's your complete car financing package, {result.customerName}</p>
      </div>

      {/* Overall Status */}
      <div className={`p-6 rounded-lg mb-6 ${result.overallApproved ? 'bg-green-50 border border-green-200' : 'bg-red-50 border border-red-200'}`}>
        <div className="flex items-center">
          <div className={`w-6 h-6 rounded-full mr-3 ${result.overallApproved ? 'bg-green-500' : 'bg-red-500'}`}></div>
          <h3 className={`text-xl font-semibold ${result.overallApproved ? 'text-green-800' : 'text-red-800'}`}>
            {result.overallApproved ? 'Congratulations! Your application is approved' : 'Application Not Approved'}
          </h3>
        </div>
        <p className={`mt-2 ${result.overallApproved ? 'text-green-700' : 'text-red-700'}`}>
          {result.overallApproved 
            ? 'You can proceed with your car purchase and financing.'
            : 'Unfortunately, we cannot approve your application at this time.'}
        </p>
      </div>

      <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-6">
        {/* Car Details */}
        <div className="bg-white rounded-lg shadow-md p-6">
          <h3 className="text-xl font-semibold text-gray-900 mb-4">Car Details</h3>
          <div className="space-y-2">
            <div className="flex justify-between">
              <span className="text-gray-600">Model:</span>
              <span className="font-medium">{result.carDetails.brand} {result.carDetails.model}</span>
            </div>
            <div className="flex justify-between">
              <span className="text-gray-600">Year:</span>
              <span className="font-medium">{result.carDetails.year}</span>
            </div>
            <div className="flex justify-between">
              <span className="text-gray-600">Category:</span>
              <span className="font-medium">{result.carDetails.category}</span>
            </div>
            <div className="flex justify-between">
              <span className="text-gray-600">Fuel Type:</span>
              <span className="font-medium">{result.carDetails.fuelType}</span>
            </div>
            <div className="flex justify-between">
              <span className="text-gray-600">Price:</span>
              <span className="font-bold text-blue-600">${result.carDetails.price.toLocaleString()}</span>
            </div>
          </div>
        </div>

        {/* Insurance Quote */}
        <div className="bg-white rounded-lg shadow-md p-6">
          <h3 className="text-xl font-semibold text-gray-900 mb-4">Insurance Quote</h3>
          <div className="space-y-2">
            <div className="flex justify-between">
              <span className="text-gray-600">Provider:</span>
              <span className="font-medium">{result.insuranceQuote.provider}</span>
            </div>
            <div className="flex justify-between">
              <span className="text-gray-600">Coverage:</span>
              <span className="font-medium">{result.insuranceQuote.coverage}</span>
            </div>
            <div className="flex justify-between">
              <span className="text-gray-600">Term:</span>
              <span className="font-medium">{result.insuranceQuote.termMonths} months</span>
            </div>
            <div className="flex justify-between">
              <span className="text-gray-600">Annual Premium:</span>
              <span className="font-bold text-green-600">${result.insuranceQuote.premium.toLocaleString()}</span>
            </div>
          </div>
        </div>

        {/* Loan Approval */}
        <div className="bg-white rounded-lg shadow-md p-6">
          <h3 className="text-xl font-semibold text-gray-900 mb-4">Loan Details</h3>
          <div className="space-y-2">
            <div className="flex justify-between">
              <span className="text-gray-600">Bank:</span>
              <span className="font-medium">{result.loanApproval.bankName}</span>
            </div>
            <div className="flex justify-between">
              <span className="text-gray-600">Status:</span>
              <span className={`font-medium ${result.loanApproval.approved ? 'text-green-600' : 'text-red-600'}`}>
                {result.loanApproval.approved ? 'Approved' : 'Rejected'}
              </span>
            </div>
            {result.loanApproval.approved && (
              <>
                <div className="flex justify-between">
                  <span className="text-gray-600">Amount:</span>
                  <span className="font-bold text-blue-600">${result.loanApproval.approvedAmount.toLocaleString()}</span>
                </div>
                <div className="flex justify-between">
                  <span className="text-gray-600">Interest Rate:</span>
                  <span className="font-medium">{result.loanApproval.interestRate}%</span>
                </div>
                <div className="flex justify-between">
                  <span className="text-gray-600">Term:</span>
                  <span className="font-medium">{result.loanApproval.termMonths} months</span>
                </div>
              </>
            )}
            <div className="pt-2">
              <span className="text-gray-600">Reason:</span>
              <p className="text-sm text-gray-700 mt-1">{result.loanApproval.reason}</p>
            </div>
          </div>
        </div>
      </div>

      {/* Monthly Payment Calculation */}
      {result.overallApproved && (
        <div className="bg-white rounded-lg shadow-md p-6 mt-6">
          <h3 className="text-xl font-semibold text-gray-900 mb-4">Monthly Payment Breakdown</h3>
          <div className="grid md:grid-cols-2 gap-6">
            <div>
              <h4 className="font-medium text-gray-700 mb-2">Loan Payment</h4>
              <p className="text-2xl font-bold text-blue-600">
                ${Math.round((result.loanApproval.approvedAmount * (result.loanApproval.interestRate / 100 / 12)) / (1 - Math.pow(1 + (result.loanApproval.interestRate / 100 / 12), -result.loanApproval.termMonths))).toLocaleString()}
              </p>
              <p className="text-sm text-gray-600">per month for {result.loanApproval.termMonths} months</p>
            </div>
            <div>
              <h4 className="font-medium text-gray-700 mb-2">Insurance Payment</h4>
              <p className="text-2xl font-bold text-green-600">
                ${Math.round(result.insuranceQuote.premium / 12).toLocaleString()}
              </p>
              <p className="text-sm text-gray-600">per month (annual premium divided by 12)</p>
            </div>
          </div>
        </div>
      )}

      <div className="mt-8 flex justify-center">
        <button
          onClick={handleNewApplication}
          className="bg-blue-600 text-white py-3 px-8 rounded-md hover:bg-blue-700 focus:ring-2 focus:ring-blue-500 focus:ring-offset-2 transition-colors"
        >
          Start New Application
        </button>
      </div>
    </div>
  )
}
